import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IPower } from 'app/shared/model/power.model';
import { PowerService } from './power.service';

@Component({
    selector: 'jhi-power-update',
    templateUrl: './power-update.component.html'
})
export class PowerUpdateComponent implements OnInit {
    private _power: IPower;
    isSaving: boolean;

    constructor(private powerService: PowerService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ power }) => {
            this.power = power;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.power.id !== undefined) {
            this.subscribeToSaveResponse(this.powerService.update(this.power));
        } else {
            this.subscribeToSaveResponse(this.powerService.create(this.power));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPower>>) {
        result.subscribe((res: HttpResponse<IPower>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get power() {
        return this._power;
    }

    set power(power: IPower) {
        this._power = power;
    }
}
