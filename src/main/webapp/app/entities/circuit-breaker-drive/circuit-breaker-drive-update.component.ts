import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ICircuitBreakerDrive } from 'app/shared/model/circuit-breaker-drive.model';
import { CircuitBreakerDriveService } from './circuit-breaker-drive.service';

@Component({
    selector: 'jhi-circuit-breaker-drive-update',
    templateUrl: './circuit-breaker-drive-update.component.html'
})
export class CircuitBreakerDriveUpdateComponent implements OnInit {
    private _circuitBreakerDrive: ICircuitBreakerDrive;
    isSaving: boolean;

    constructor(private circuitBreakerDriveService: CircuitBreakerDriveService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ circuitBreakerDrive }) => {
            this.circuitBreakerDrive = circuitBreakerDrive;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.circuitBreakerDrive.id !== undefined) {
            this.subscribeToSaveResponse(this.circuitBreakerDriveService.update(this.circuitBreakerDrive));
        } else {
            this.subscribeToSaveResponse(this.circuitBreakerDriveService.create(this.circuitBreakerDrive));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICircuitBreakerDrive>>) {
        result.subscribe((res: HttpResponse<ICircuitBreakerDrive>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get circuitBreakerDrive() {
        return this._circuitBreakerDrive;
    }

    set circuitBreakerDrive(circuitBreakerDrive: ICircuitBreakerDrive) {
        this._circuitBreakerDrive = circuitBreakerDrive;
    }
}
