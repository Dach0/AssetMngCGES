import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ICircuitBreakerType } from 'app/shared/model/circuit-breaker-type.model';
import { CircuitBreakerTypeService } from './circuit-breaker-type.service';

@Component({
    selector: 'jhi-circuit-breaker-type-update',
    templateUrl: './circuit-breaker-type-update.component.html'
})
export class CircuitBreakerTypeUpdateComponent implements OnInit {
    private _circuitBreakerType: ICircuitBreakerType;
    isSaving: boolean;

    constructor(private circuitBreakerTypeService: CircuitBreakerTypeService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ circuitBreakerType }) => {
            this.circuitBreakerType = circuitBreakerType;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.circuitBreakerType.id !== undefined) {
            this.subscribeToSaveResponse(this.circuitBreakerTypeService.update(this.circuitBreakerType));
        } else {
            this.subscribeToSaveResponse(this.circuitBreakerTypeService.create(this.circuitBreakerType));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICircuitBreakerType>>) {
        result.subscribe((res: HttpResponse<ICircuitBreakerType>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get circuitBreakerType() {
        return this._circuitBreakerType;
    }

    set circuitBreakerType(circuitBreakerType: ICircuitBreakerType) {
        this._circuitBreakerType = circuitBreakerType;
    }
}
