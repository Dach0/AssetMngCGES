import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IVoltageLevel } from 'app/shared/model/voltage-level.model';
import { VoltageLevelService } from './voltage-level.service';
import { ICorrectionFactor } from 'app/shared/model/correction-factor.model';
import { CorrectionFactorService } from 'app/entities/correction-factor';

@Component({
    selector: 'jhi-voltage-level-update',
    templateUrl: './voltage-level-update.component.html'
})
export class VoltageLevelUpdateComponent implements OnInit {
    private _voltageLevel: IVoltageLevel;
    isSaving: boolean;

    correctionfactors: ICorrectionFactor[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private voltageLevelService: VoltageLevelService,
        private correctionFactorService: CorrectionFactorService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ voltageLevel }) => {
            this.voltageLevel = voltageLevel;
        });
        this.correctionFactorService.query().subscribe(
            (res: HttpResponse<ICorrectionFactor[]>) => {
                this.correctionfactors = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.voltageLevel.id !== undefined) {
            this.subscribeToSaveResponse(this.voltageLevelService.update(this.voltageLevel));
        } else {
            this.subscribeToSaveResponse(this.voltageLevelService.create(this.voltageLevel));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IVoltageLevel>>) {
        result.subscribe((res: HttpResponse<IVoltageLevel>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackCorrectionFactorById(index: number, item: ICorrectionFactor) {
        return item.id;
    }
    get voltageLevel() {
        return this._voltageLevel;
    }

    set voltageLevel(voltageLevel: IVoltageLevel) {
        this._voltageLevel = voltageLevel;
    }
}
