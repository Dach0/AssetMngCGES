import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISurgeArrester } from 'app/shared/model/surge-arrester.model';
import { Principal } from 'app/core';
import { SurgeArresterService } from './surge-arrester.service';

@Component({
    selector: 'jhi-surge-arrester',
    templateUrl: './surge-arrester.component.html'
})
export class SurgeArresterComponent implements OnInit, OnDestroy {
    surgeArresters: ISurgeArrester[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private surgeArresterService: SurgeArresterService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.surgeArresterService.query().subscribe(
            (res: HttpResponse<ISurgeArrester[]>) => {
                this.surgeArresters = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSurgeArresters();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISurgeArrester) {
        return item.id;
    }

    registerChangeInSurgeArresters() {
        this.eventSubscriber = this.eventManager.subscribe('surgeArresterListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
