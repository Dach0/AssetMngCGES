import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IElementStatus } from 'app/shared/model/element-status.model';
import { Principal } from 'app/core';
import { ElementStatusService } from './element-status.service';

@Component({
    selector: 'jhi-element-status',
    templateUrl: './element-status.component.html'
})
export class ElementStatusComponent implements OnInit, OnDestroy {
    elementStatuses: IElementStatus[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private elementStatusService: ElementStatusService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.elementStatusService.query().subscribe(
            (res: HttpResponse<IElementStatus[]>) => {
                this.elementStatuses = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInElementStatuses();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IElementStatus) {
        return item.id;
    }

    registerChangeInElementStatuses() {
        this.eventSubscriber = this.eventManager.subscribe('elementStatusListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
