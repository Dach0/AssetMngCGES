import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IDisconnectorType } from 'app/shared/model/disconnector-type.model';
import { Principal } from 'app/core';
import { DisconnectorTypeService } from './disconnector-type.service';

@Component({
    selector: 'jhi-disconnector-type',
    templateUrl: './disconnector-type.component.html'
})
export class DisconnectorTypeComponent implements OnInit, OnDestroy {
    disconnectorTypes: IDisconnectorType[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private disconnectorTypeService: DisconnectorTypeService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.disconnectorTypeService.query().subscribe(
            (res: HttpResponse<IDisconnectorType[]>) => {
                this.disconnectorTypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInDisconnectorTypes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IDisconnectorType) {
        return item.id;
    }

    registerChangeInDisconnectorTypes() {
        this.eventSubscriber = this.eventManager.subscribe('disconnectorTypeListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
