import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ITconnection } from 'app/shared/model/tconnection.model';
import { Principal } from 'app/core';
import { TconnectionService } from './tconnection.service';

@Component({
    selector: 'jhi-tconnection',
    templateUrl: './tconnection.component.html'
})
export class TconnectionComponent implements OnInit, OnDestroy {
    tconnections: ITconnection[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private tconnectionService: TconnectionService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.tconnectionService.query().subscribe(
            (res: HttpResponse<ITconnection[]>) => {
                this.tconnections = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInTconnections();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ITconnection) {
        return item.id;
    }

    registerChangeInTconnections() {
        this.eventSubscriber = this.eventManager.subscribe('tconnectionListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
