import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IAssetCondition } from 'app/shared/model/asset-condition.model';
import { Principal } from 'app/core';
import { AssetConditionService } from './asset-condition.service';

@Component({
    selector: 'jhi-asset-condition',
    templateUrl: './asset-condition.component.html'
})
export class AssetConditionComponent implements OnInit, OnDestroy {
    assetConditions: IAssetCondition[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private assetConditionService: AssetConditionService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.assetConditionService.query().subscribe(
            (res: HttpResponse<IAssetCondition[]>) => {
                this.assetConditions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInAssetConditions();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IAssetCondition) {
        return item.id;
    }

    registerChangeInAssetConditions() {
        this.eventSubscriber = this.eventManager.subscribe('assetConditionListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
