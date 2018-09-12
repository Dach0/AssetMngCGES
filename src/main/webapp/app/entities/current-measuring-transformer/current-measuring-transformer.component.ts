import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ICurrentMeasuringTransformer } from 'app/shared/model/current-measuring-transformer.model';
import { Principal } from 'app/core';
import { CurrentMeasuringTransformerService } from './current-measuring-transformer.service';

@Component({
    selector: 'jhi-current-measuring-transformer',
    templateUrl: './current-measuring-transformer.component.html'
})
export class CurrentMeasuringTransformerComponent implements OnInit, OnDestroy {
    currentMeasuringTransformers: ICurrentMeasuringTransformer[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private currentMeasuringTransformerService: CurrentMeasuringTransformerService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.currentMeasuringTransformerService.query().subscribe(
            (res: HttpResponse<ICurrentMeasuringTransformer[]>) => {
                this.currentMeasuringTransformers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInCurrentMeasuringTransformers();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICurrentMeasuringTransformer) {
        return item.id;
    }

    registerChangeInCurrentMeasuringTransformers() {
        this.eventSubscriber = this.eventManager.subscribe('currentMeasuringTransformerListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
