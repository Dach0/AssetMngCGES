import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISubstation } from 'app/shared/model/substation.model';
import { Principal } from 'app/core';
import { SubstationService } from './substation.service';

@Component({
    selector: 'jhi-substation',
    templateUrl: './substation.component.html'
})
export class SubstationComponent implements OnInit, OnDestroy {
    substations: ISubstation[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private substationService: SubstationService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.substationService.query().subscribe(
            (res: HttpResponse<ISubstation[]>) => {
                this.substations = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSubstations();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISubstation) {
        return item.id;
    }

    registerChangeInSubstations() {
        this.eventSubscriber = this.eventManager.subscribe('substationListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
