import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IGroundSticksType } from 'app/shared/model/ground-sticks-type.model';
import { Principal } from 'app/core';
import { GroundSticksTypeService } from './ground-sticks-type.service';

@Component({
    selector: 'jhi-ground-sticks-type',
    templateUrl: './ground-sticks-type.component.html'
})
export class GroundSticksTypeComponent implements OnInit, OnDestroy {
    groundSticksTypes: IGroundSticksType[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private groundSticksTypeService: GroundSticksTypeService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.groundSticksTypeService.query().subscribe(
            (res: HttpResponse<IGroundSticksType[]>) => {
                this.groundSticksTypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInGroundSticksTypes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IGroundSticksType) {
        return item.id;
    }

    registerChangeInGroundSticksTypes() {
        this.eventSubscriber = this.eventManager.subscribe('groundSticksTypeListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
