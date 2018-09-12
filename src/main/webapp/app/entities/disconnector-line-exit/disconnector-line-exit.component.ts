import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IDisconnectorLineExit } from 'app/shared/model/disconnector-line-exit.model';
import { Principal } from 'app/core';
import { DisconnectorLineExitService } from './disconnector-line-exit.service';

@Component({
    selector: 'jhi-disconnector-line-exit',
    templateUrl: './disconnector-line-exit.component.html'
})
export class DisconnectorLineExitComponent implements OnInit, OnDestroy {
    disconnectorLineExits: IDisconnectorLineExit[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private disconnectorLineExitService: DisconnectorLineExitService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.disconnectorLineExitService.query().subscribe(
            (res: HttpResponse<IDisconnectorLineExit[]>) => {
                this.disconnectorLineExits = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInDisconnectorLineExits();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IDisconnectorLineExit) {
        return item.id;
    }

    registerChangeInDisconnectorLineExits() {
        this.eventSubscriber = this.eventManager.subscribe('disconnectorLineExitListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
