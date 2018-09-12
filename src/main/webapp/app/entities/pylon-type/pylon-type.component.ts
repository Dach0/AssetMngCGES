import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IPylonType } from 'app/shared/model/pylon-type.model';
import { Principal } from 'app/core';
import { PylonTypeService } from './pylon-type.service';

@Component({
    selector: 'jhi-pylon-type',
    templateUrl: './pylon-type.component.html'
})
export class PylonTypeComponent implements OnInit, OnDestroy {
    pylonTypes: IPylonType[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private pylonTypeService: PylonTypeService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.pylonTypeService.query().subscribe(
            (res: HttpResponse<IPylonType[]>) => {
                this.pylonTypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInPylonTypes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IPylonType) {
        return item.id;
    }

    registerChangeInPylonTypes() {
        this.eventSubscriber = this.eventManager.subscribe('pylonTypeListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
