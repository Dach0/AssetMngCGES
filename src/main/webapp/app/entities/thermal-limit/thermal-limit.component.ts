import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IThermalLimit } from 'app/shared/model/thermal-limit.model';
import { Principal } from 'app/core';
import { ThermalLimitService } from './thermal-limit.service';

@Component({
    selector: 'jhi-thermal-limit',
    templateUrl: './thermal-limit.component.html'
})
export class ThermalLimitComponent implements OnInit, OnDestroy {
    thermalLimits: IThermalLimit[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private thermalLimitService: ThermalLimitService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.thermalLimitService.query().subscribe(
            (res: HttpResponse<IThermalLimit[]>) => {
                this.thermalLimits = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInThermalLimits();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IThermalLimit) {
        return item.id;
    }

    registerChangeInThermalLimits() {
        this.eventSubscriber = this.eventManager.subscribe('thermalLimitListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
