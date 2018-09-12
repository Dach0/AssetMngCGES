import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IJobStatus } from 'app/shared/model/job-status.model';
import { Principal } from 'app/core';
import { JobStatusService } from './job-status.service';

@Component({
    selector: 'jhi-job-status',
    templateUrl: './job-status.component.html'
})
export class JobStatusComponent implements OnInit, OnDestroy {
    jobStatuses: IJobStatus[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private jobStatusService: JobStatusService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.jobStatusService.query().subscribe(
            (res: HttpResponse<IJobStatus[]>) => {
                this.jobStatuses = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInJobStatuses();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IJobStatus) {
        return item.id;
    }

    registerChangeInJobStatuses() {
        this.eventSubscriber = this.eventManager.subscribe('jobStatusListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
