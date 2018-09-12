import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISurgeArresterType } from 'app/shared/model/surge-arrester-type.model';
import { Principal } from 'app/core';
import { SurgeArresterTypeService } from './surge-arrester-type.service';

@Component({
    selector: 'jhi-surge-arrester-type',
    templateUrl: './surge-arrester-type.component.html'
})
export class SurgeArresterTypeComponent implements OnInit, OnDestroy {
    surgeArresterTypes: ISurgeArresterType[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private surgeArresterTypeService: SurgeArresterTypeService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.surgeArresterTypeService.query().subscribe(
            (res: HttpResponse<ISurgeArresterType[]>) => {
                this.surgeArresterTypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSurgeArresterTypes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISurgeArresterType) {
        return item.id;
    }

    registerChangeInSurgeArresterTypes() {
        this.eventSubscriber = this.eventManager.subscribe('surgeArresterTypeListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
