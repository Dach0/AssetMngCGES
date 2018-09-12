import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IVoltageMeasuringTransformer } from 'app/shared/model/voltage-measuring-transformer.model';
import { Principal } from 'app/core';
import { VoltageMeasuringTransformerService } from './voltage-measuring-transformer.service';

@Component({
    selector: 'jhi-voltage-measuring-transformer',
    templateUrl: './voltage-measuring-transformer.component.html'
})
export class VoltageMeasuringTransformerComponent implements OnInit, OnDestroy {
    voltageMeasuringTransformers: IVoltageMeasuringTransformer[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private voltageMeasuringTransformerService: VoltageMeasuringTransformerService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.voltageMeasuringTransformerService.query().subscribe(
            (res: HttpResponse<IVoltageMeasuringTransformer[]>) => {
                this.voltageMeasuringTransformers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInVoltageMeasuringTransformers();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IVoltageMeasuringTransformer) {
        return item.id;
    }

    registerChangeInVoltageMeasuringTransformers() {
        this.eventSubscriber = this.eventManager.subscribe('voltageMeasuringTransformerListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
