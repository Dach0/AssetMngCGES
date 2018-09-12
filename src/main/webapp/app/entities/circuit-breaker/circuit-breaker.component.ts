import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ICircuitBreaker } from 'app/shared/model/circuit-breaker.model';
import { Principal } from 'app/core';
import { CircuitBreakerService } from './circuit-breaker.service';

@Component({
    selector: 'jhi-circuit-breaker',
    templateUrl: './circuit-breaker.component.html'
})
export class CircuitBreakerComponent implements OnInit, OnDestroy {
    circuitBreakers: ICircuitBreaker[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private circuitBreakerService: CircuitBreakerService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.circuitBreakerService.query().subscribe(
            (res: HttpResponse<ICircuitBreaker[]>) => {
                this.circuitBreakers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInCircuitBreakers();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICircuitBreaker) {
        return item.id;
    }

    registerChangeInCircuitBreakers() {
        this.eventSubscriber = this.eventManager.subscribe('circuitBreakerListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
