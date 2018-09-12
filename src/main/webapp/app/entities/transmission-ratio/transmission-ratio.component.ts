import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ITransmissionRatio } from 'app/shared/model/transmission-ratio.model';
import { Principal } from 'app/core';
import { TransmissionRatioService } from './transmission-ratio.service';

@Component({
    selector: 'jhi-transmission-ratio',
    templateUrl: './transmission-ratio.component.html'
})
export class TransmissionRatioComponent implements OnInit, OnDestroy {
    transmissionRatios: ITransmissionRatio[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private transmissionRatioService: TransmissionRatioService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.transmissionRatioService.query().subscribe(
            (res: HttpResponse<ITransmissionRatio[]>) => {
                this.transmissionRatios = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInTransmissionRatios();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ITransmissionRatio) {
        return item.id;
    }

    registerChangeInTransmissionRatios() {
        this.eventSubscriber = this.eventManager.subscribe('transmissionRatioListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
