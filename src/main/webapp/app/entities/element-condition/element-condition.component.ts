import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IElementCondition } from 'app/shared/model/element-condition.model';
import { Principal } from 'app/core';
import { ElementConditionService } from './element-condition.service';

@Component({
    selector: 'jhi-element-condition',
    templateUrl: './element-condition.component.html'
})
export class ElementConditionComponent implements OnInit, OnDestroy {
    elementConditions: IElementCondition[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private elementConditionService: ElementConditionService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.elementConditionService.query().subscribe(
            (res: HttpResponse<IElementCondition[]>) => {
                this.elementConditions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInElementConditions();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IElementCondition) {
        return item.id;
    }

    registerChangeInElementConditions() {
        this.eventSubscriber = this.eventManager.subscribe('elementConditionListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
