import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IAssetStatus } from 'app/shared/model/asset-status.model';
import { Principal } from 'app/core';
import { AssetStatusService } from './asset-status.service';

@Component({
    selector: 'jhi-asset-status',
    templateUrl: './asset-status.component.html'
})
export class AssetStatusComponent implements OnInit, OnDestroy {
    assetStatuses: IAssetStatus[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private assetStatusService: AssetStatusService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.assetStatusService.query().subscribe(
            (res: HttpResponse<IAssetStatus[]>) => {
                this.assetStatuses = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInAssetStatuses();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IAssetStatus) {
        return item.id;
    }

    registerChangeInAssetStatuses() {
        this.eventSubscriber = this.eventManager.subscribe('assetStatusListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
