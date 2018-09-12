import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ITransformer } from 'app/shared/model/transformer.model';
import { Principal } from 'app/core';
import { TransformerService } from './transformer.service';

@Component({
    selector: 'jhi-transformer',
    templateUrl: './transformer.component.html'
})
export class TransformerComponent implements OnInit, OnDestroy {
    transformers: ITransformer[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private transformerService: TransformerService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.transformerService.query().subscribe(
            (res: HttpResponse<ITransformer[]>) => {
                this.transformers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInTransformers();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ITransformer) {
        return item.id;
    }

    registerChangeInTransformers() {
        this.eventSubscriber = this.eventManager.subscribe('transformerListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
