import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ITransformatorNumber } from 'app/shared/model/transformator-number.model';
import { Principal } from 'app/core';
import { TransformatorNumberService } from './transformator-number.service';

@Component({
    selector: 'jhi-transformator-number',
    templateUrl: './transformator-number.component.html'
})
export class TransformatorNumberComponent implements OnInit, OnDestroy {
    transformatorNumbers: ITransformatorNumber[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private transformatorNumberService: TransformatorNumberService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.transformatorNumberService.query().subscribe(
            (res: HttpResponse<ITransformatorNumber[]>) => {
                this.transformatorNumbers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInTransformatorNumbers();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ITransformatorNumber) {
        return item.id;
    }

    registerChangeInTransformatorNumbers() {
        this.eventSubscriber = this.eventManager.subscribe('transformatorNumberListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
