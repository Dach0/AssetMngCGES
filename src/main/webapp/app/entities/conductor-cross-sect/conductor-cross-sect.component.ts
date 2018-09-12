import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IConductorCrossSect } from 'app/shared/model/conductor-cross-sect.model';
import { Principal } from 'app/core';
import { ConductorCrossSectService } from './conductor-cross-sect.service';

@Component({
    selector: 'jhi-conductor-cross-sect',
    templateUrl: './conductor-cross-sect.component.html'
})
export class ConductorCrossSectComponent implements OnInit, OnDestroy {
    conductorCrossSects: IConductorCrossSect[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private conductorCrossSectService: ConductorCrossSectService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.conductorCrossSectService.query().subscribe(
            (res: HttpResponse<IConductorCrossSect[]>) => {
                this.conductorCrossSects = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInConductorCrossSects();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IConductorCrossSect) {
        return item.id;
    }

    registerChangeInConductorCrossSects() {
        this.eventSubscriber = this.eventManager.subscribe('conductorCrossSectListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
