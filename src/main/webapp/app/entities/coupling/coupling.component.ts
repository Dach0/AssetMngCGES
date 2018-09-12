import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ICoupling } from 'app/shared/model/coupling.model';
import { Principal } from 'app/core';
import { CouplingService } from './coupling.service';

@Component({
    selector: 'jhi-coupling',
    templateUrl: './coupling.component.html'
})
export class CouplingComponent implements OnInit, OnDestroy {
    couplings: ICoupling[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private couplingService: CouplingService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.couplingService.query().subscribe(
            (res: HttpResponse<ICoupling[]>) => {
                this.couplings = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInCouplings();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICoupling) {
        return item.id;
    }

    registerChangeInCouplings() {
        this.eventSubscriber = this.eventManager.subscribe('couplingListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
