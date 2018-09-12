import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ICircuitBreaker } from 'app/shared/model/circuit-breaker.model';
import { CircuitBreakerService } from './circuit-breaker.service';
import { ICircuitBreakerDrive } from 'app/shared/model/circuit-breaker-drive.model';
import { CircuitBreakerDriveService } from 'app/entities/circuit-breaker-drive';
import { ICircuitBreakerType } from 'app/shared/model/circuit-breaker-type.model';
import { CircuitBreakerTypeService } from 'app/entities/circuit-breaker-type';
import { IManufacturer } from 'app/shared/model/manufacturer.model';
import { ManufacturerService } from 'app/entities/manufacturer';
import { ISubstation } from 'app/shared/model/substation.model';
import { SubstationService } from 'app/entities/substation';
import { IField } from 'app/shared/model/field.model';
import { FieldService } from 'app/entities/field';

@Component({
    selector: 'jhi-circuit-breaker-update',
    templateUrl: './circuit-breaker-update.component.html'
})
export class CircuitBreakerUpdateComponent implements OnInit {
    private _circuitBreaker: ICircuitBreaker;
    isSaving: boolean;

    circuitbreakerdrives: ICircuitBreakerDrive[];

    circuitbreakertypes: ICircuitBreakerType[];

    manufacturers: IManufacturer[];

    substations: ISubstation[];

    fields: IField[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private circuitBreakerService: CircuitBreakerService,
        private circuitBreakerDriveService: CircuitBreakerDriveService,
        private circuitBreakerTypeService: CircuitBreakerTypeService,
        private manufacturerService: ManufacturerService,
        private substationService: SubstationService,
        private fieldService: FieldService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ circuitBreaker }) => {
            this.circuitBreaker = circuitBreaker;
        });
        this.circuitBreakerDriveService.query().subscribe(
            (res: HttpResponse<ICircuitBreakerDrive[]>) => {
                this.circuitbreakerdrives = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.circuitBreakerTypeService.query().subscribe(
            (res: HttpResponse<ICircuitBreakerType[]>) => {
                this.circuitbreakertypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.manufacturerService.query().subscribe(
            (res: HttpResponse<IManufacturer[]>) => {
                this.manufacturers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.substationService.query().subscribe(
            (res: HttpResponse<ISubstation[]>) => {
                this.substations = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.fieldService.query().subscribe(
            (res: HttpResponse<IField[]>) => {
                this.fields = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.circuitBreaker.id !== undefined) {
            this.subscribeToSaveResponse(this.circuitBreakerService.update(this.circuitBreaker));
        } else {
            this.subscribeToSaveResponse(this.circuitBreakerService.create(this.circuitBreaker));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICircuitBreaker>>) {
        result.subscribe((res: HttpResponse<ICircuitBreaker>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackCircuitBreakerDriveById(index: number, item: ICircuitBreakerDrive) {
        return item.id;
    }

    trackCircuitBreakerTypeById(index: number, item: ICircuitBreakerType) {
        return item.id;
    }

    trackManufacturerById(index: number, item: IManufacturer) {
        return item.id;
    }

    trackSubstationById(index: number, item: ISubstation) {
        return item.id;
    }

    trackFieldById(index: number, item: IField) {
        return item.id;
    }
    get circuitBreaker() {
        return this._circuitBreaker;
    }

    set circuitBreaker(circuitBreaker: ICircuitBreaker) {
        this._circuitBreaker = circuitBreaker;
    }
}
