import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IConductorCrossSect } from 'app/shared/model/conductor-cross-sect.model';
import { ConductorCrossSectService } from './conductor-cross-sect.service';

@Component({
    selector: 'jhi-conductor-cross-sect-update',
    templateUrl: './conductor-cross-sect-update.component.html'
})
export class ConductorCrossSectUpdateComponent implements OnInit {
    private _conductorCrossSect: IConductorCrossSect;
    isSaving: boolean;

    constructor(private conductorCrossSectService: ConductorCrossSectService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ conductorCrossSect }) => {
            this.conductorCrossSect = conductorCrossSect;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.conductorCrossSect.id !== undefined) {
            this.subscribeToSaveResponse(this.conductorCrossSectService.update(this.conductorCrossSect));
        } else {
            this.subscribeToSaveResponse(this.conductorCrossSectService.create(this.conductorCrossSect));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IConductorCrossSect>>) {
        result.subscribe((res: HttpResponse<IConductorCrossSect>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get conductorCrossSect() {
        return this._conductorCrossSect;
    }

    set conductorCrossSect(conductorCrossSect: IConductorCrossSect) {
        this._conductorCrossSect = conductorCrossSect;
    }
}
