import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IDisconnectorBusBar } from 'app/shared/model/disconnector-bus-bar.model';
import { Principal } from 'app/core';
import { DisconnectorBusBarService } from './disconnector-bus-bar.service';

@Component({
    selector: 'jhi-disconnector-bus-bar',
    templateUrl: './disconnector-bus-bar.component.html'
})
export class DisconnectorBusBarComponent implements OnInit, OnDestroy {
    disconnectorBusBars: IDisconnectorBusBar[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private disconnectorBusBarService: DisconnectorBusBarService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.disconnectorBusBarService.query().subscribe(
            (res: HttpResponse<IDisconnectorBusBar[]>) => {
                this.disconnectorBusBars = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInDisconnectorBusBars();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IDisconnectorBusBar) {
        return item.id;
    }

    registerChangeInDisconnectorBusBars() {
        this.eventSubscriber = this.eventManager.subscribe('disconnectorBusBarListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
