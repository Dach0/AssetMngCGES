import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ISubstation } from 'app/shared/model/substation.model';
import { SubstationService } from './substation.service';
import { IFacility } from 'app/shared/model/facility.model';
import { FacilityService } from 'app/entities/facility';

@Component({
    selector: 'jhi-substation-update',
    templateUrl: './substation-update.component.html'
})
export class SubstationUpdateComponent implements OnInit {
    private _substation: ISubstation;
    isSaving: boolean;

    facilities: IFacility[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private substationService: SubstationService,
        private facilityService: FacilityService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ substation }) => {
            this.substation = substation;
        });
        this.facilityService.query().subscribe(
            (res: HttpResponse<IFacility[]>) => {
                this.facilities = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.substation.id !== undefined) {
            this.subscribeToSaveResponse(this.substationService.update(this.substation));
        } else {
            this.subscribeToSaveResponse(this.substationService.create(this.substation));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISubstation>>) {
        result.subscribe((res: HttpResponse<ISubstation>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackFacilityById(index: number, item: IFacility) {
        return item.id;
    }
    get substation() {
        return this._substation;
    }

    set substation(substation: ISubstation) {
        this._substation = substation;
    }
}
