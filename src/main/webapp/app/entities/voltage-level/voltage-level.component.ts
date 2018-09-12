import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IVoltageLevel } from 'app/shared/model/voltage-level.model';
import { Principal } from 'app/core';
import { VoltageLevelService } from './voltage-level.service';

@Component({
    selector: 'jhi-voltage-level',
    templateUrl: './voltage-level.component.html'
})
export class VoltageLevelComponent implements OnInit, OnDestroy {
    voltageLevels: IVoltageLevel[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private voltageLevelService: VoltageLevelService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.voltageLevelService.query().subscribe(
            (res: HttpResponse<IVoltageLevel[]>) => {
                this.voltageLevels = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInVoltageLevels();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IVoltageLevel) {
        return item.id;
    }

    registerChangeInVoltageLevels() {
        this.eventSubscriber = this.eventManager.subscribe('voltageLevelListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
