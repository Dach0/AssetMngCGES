import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IFacilityMaintainingCo } from 'app/shared/model/facility-maintaining-co.model';
import { FacilityMaintainingCoService } from './facility-maintaining-co.service';

@Component({
    selector: 'jhi-facility-maintaining-co-update',
    templateUrl: './facility-maintaining-co-update.component.html'
})
export class FacilityMaintainingCoUpdateComponent implements OnInit {
    private _facilityMaintainingCo: IFacilityMaintainingCo;
    isSaving: boolean;

    constructor(private facilityMaintainingCoService: FacilityMaintainingCoService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ facilityMaintainingCo }) => {
            this.facilityMaintainingCo = facilityMaintainingCo;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.facilityMaintainingCo.id !== undefined) {
            this.subscribeToSaveResponse(this.facilityMaintainingCoService.update(this.facilityMaintainingCo));
        } else {
            this.subscribeToSaveResponse(this.facilityMaintainingCoService.create(this.facilityMaintainingCo));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IFacilityMaintainingCo>>) {
        result.subscribe(
            (res: HttpResponse<IFacilityMaintainingCo>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get facilityMaintainingCo() {
        return this._facilityMaintainingCo;
    }

    set facilityMaintainingCo(facilityMaintainingCo: IFacilityMaintainingCo) {
        this._facilityMaintainingCo = facilityMaintainingCo;
    }
}
