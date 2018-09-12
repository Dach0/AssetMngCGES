import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ICircuitBreakerDrive } from 'app/shared/model/circuit-breaker-drive.model';
import { Principal } from 'app/core';
import { CircuitBreakerDriveService } from './circuit-breaker-drive.service';

@Component({
    selector: 'jhi-circuit-breaker-drive',
    templateUrl: './circuit-breaker-drive.component.html'
})
export class CircuitBreakerDriveComponent implements OnInit, OnDestroy {
    circuitBreakerDrives: ICircuitBreakerDrive[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private circuitBreakerDriveService: CircuitBreakerDriveService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.circuitBreakerDriveService.query().subscribe(
            (res: HttpResponse<ICircuitBreakerDrive[]>) => {
                this.circuitBreakerDrives = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInCircuitBreakerDrives();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICircuitBreakerDrive) {
        return item.id;
    }

    registerChangeInCircuitBreakerDrives() {
        this.eventSubscriber = this.eventManager.subscribe('circuitBreakerDriveListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
