import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ITransformerType } from 'app/shared/model/transformer-type.model';
import { Principal } from 'app/core';
import { TransformerTypeService } from './transformer-type.service';

@Component({
    selector: 'jhi-transformer-type',
    templateUrl: './transformer-type.component.html'
})
export class TransformerTypeComponent implements OnInit, OnDestroy {
    transformerTypes: ITransformerType[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private transformerTypeService: TransformerTypeService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.transformerTypeService.query().subscribe(
            (res: HttpResponse<ITransformerType[]>) => {
                this.transformerTypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInTransformerTypes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ITransformerType) {
        return item.id;
    }

    registerChangeInTransformerTypes() {
        this.eventSubscriber = this.eventManager.subscribe('transformerTypeListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
