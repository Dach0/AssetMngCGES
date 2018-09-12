import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IGroundingSticks } from 'app/shared/model/grounding-sticks.model';
import { Principal } from 'app/core';
import { GroundingSticksService } from './grounding-sticks.service';

@Component({
    selector: 'jhi-grounding-sticks',
    templateUrl: './grounding-sticks.component.html'
})
export class GroundingSticksComponent implements OnInit, OnDestroy {
    groundingSticks: IGroundingSticks[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private groundingSticksService: GroundingSticksService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.groundingSticksService.query().subscribe(
            (res: HttpResponse<IGroundingSticks[]>) => {
                this.groundingSticks = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInGroundingSticks();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IGroundingSticks) {
        return item.id;
    }

    registerChangeInGroundingSticks() {
        this.eventSubscriber = this.eventManager.subscribe('groundingSticksListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
