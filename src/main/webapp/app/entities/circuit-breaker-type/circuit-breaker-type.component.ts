import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ICircuitBreakerType } from 'app/shared/model/circuit-breaker-type.model';
import { Principal } from 'app/core';
import { CircuitBreakerTypeService } from './circuit-breaker-type.service';

@Component({
    selector: 'jhi-circuit-breaker-type',
    templateUrl: './circuit-breaker-type.component.html'
})
export class CircuitBreakerTypeComponent implements OnInit, OnDestroy {
    circuitBreakerTypes: ICircuitBreakerType[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private circuitBreakerTypeService: CircuitBreakerTypeService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.circuitBreakerTypeService.query().subscribe(
            (res: HttpResponse<ICircuitBreakerType[]>) => {
                this.circuitBreakerTypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInCircuitBreakerTypes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICircuitBreakerType) {
        return item.id;
    }

    registerChangeInCircuitBreakerTypes() {
        this.eventSubscriber = this.eventManager.subscribe('circuitBreakerTypeListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
