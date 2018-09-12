import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IPower } from 'app/shared/model/power.model';
import { Principal } from 'app/core';
import { PowerService } from './power.service';

@Component({
    selector: 'jhi-power',
    templateUrl: './power.component.html'
})
export class PowerComponent implements OnInit, OnDestroy {
    powers: IPower[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private powerService: PowerService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.powerService.query().subscribe(
            (res: HttpResponse<IPower[]>) => {
                this.powers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInPowers();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IPower) {
        return item.id;
    }

    registerChangeInPowers() {
        this.eventSubscriber = this.eventManager.subscribe('powerListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
