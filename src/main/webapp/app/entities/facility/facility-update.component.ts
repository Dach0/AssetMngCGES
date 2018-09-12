import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IFacility } from 'app/shared/model/facility.model';
import { FacilityService } from './facility.service';
import { IVoltageLevel } from 'app/shared/model/voltage-level.model';
import { VoltageLevelService } from 'app/entities/voltage-level';
import { IFacilityMaintainingCo } from 'app/shared/model/facility-maintaining-co.model';
import { FacilityMaintainingCoService } from 'app/entities/facility-maintaining-co';

@Component({
    selector: 'jhi-facility-update',
    templateUrl: './facility-update.component.html'
})
export class FacilityUpdateComponent implements OnInit {
    private _facility: IFacility;
    isSaving: boolean;

    voltagelevels: IVoltageLevel[];

    facilitymaintainingcos: IFacilityMaintainingCo[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private facilityService: FacilityService,
        private voltageLevelService: VoltageLevelService,
        private facilityMaintainingCoService: FacilityMaintainingCoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ facility }) => {
            this.facility = facility;
        });
        this.voltageLevelService.query().subscribe(
            (res: HttpResponse<IVoltageLevel[]>) => {
                this.voltagelevels = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.facilityMaintainingCoService.query().subscribe(
            (res: HttpResponse<IFacilityMaintainingCo[]>) => {
                this.facilitymaintainingcos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.facility.id !== undefined) {
            this.subscribeToSaveResponse(this.facilityService.update(this.facility));
        } else {
            this.subscribeToSaveResponse(this.facilityService.create(this.facility));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IFacility>>) {
        result.subscribe((res: HttpResponse<IFacility>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackVoltageLevelById(index: number, item: IVoltageLevel) {
        return item.id;
    }

    trackFacilityMaintainingCoById(index: number, item: IFacilityMaintainingCo) {
        return item.id;
    }
    get facility() {
        return this._facility;
    }

    set facility(facility: IFacility) {
        this._facility = facility;
    }
}
