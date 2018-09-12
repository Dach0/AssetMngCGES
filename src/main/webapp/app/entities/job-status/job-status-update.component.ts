import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IJobStatus } from 'app/shared/model/job-status.model';
import { JobStatusService } from './job-status.service';

@Component({
    selector: 'jhi-job-status-update',
    templateUrl: './job-status-update.component.html'
})
export class JobStatusUpdateComponent implements OnInit {
    private _jobStatus: IJobStatus;
    isSaving: boolean;

    constructor(private jobStatusService: JobStatusService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ jobStatus }) => {
            this.jobStatus = jobStatus;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.jobStatus.id !== undefined) {
            this.subscribeToSaveResponse(this.jobStatusService.update(this.jobStatus));
        } else {
            this.subscribeToSaveResponse(this.jobStatusService.create(this.jobStatus));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IJobStatus>>) {
        result.subscribe((res: HttpResponse<IJobStatus>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get jobStatus() {
        return this._jobStatus;
    }

    set jobStatus(jobStatus: IJobStatus) {
        this._jobStatus = jobStatus;
    }
}
