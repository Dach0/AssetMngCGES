import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IThermalLimit } from 'app/shared/model/thermal-limit.model';
import { ThermalLimitService } from './thermal-limit.service';

@Component({
    selector: 'jhi-thermal-limit-update',
    templateUrl: './thermal-limit-update.component.html'
})
export class ThermalLimitUpdateComponent implements OnInit {
    private _thermalLimit: IThermalLimit;
    isSaving: boolean;

    constructor(private thermalLimitService: ThermalLimitService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ thermalLimit }) => {
            this.thermalLimit = thermalLimit;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.thermalLimit.id !== undefined) {
            this.subscribeToSaveResponse(this.thermalLimitService.update(this.thermalLimit));
        } else {
            this.subscribeToSaveResponse(this.thermalLimitService.create(this.thermalLimit));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IThermalLimit>>) {
        result.subscribe((res: HttpResponse<IThermalLimit>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get thermalLimit() {
        return this._thermalLimit;
    }

    set thermalLimit(thermalLimit: IThermalLimit) {
        this._thermalLimit = thermalLimit;
    }
}
