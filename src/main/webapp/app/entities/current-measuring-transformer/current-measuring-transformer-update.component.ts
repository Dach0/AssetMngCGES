import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ICurrentMeasuringTransformer } from 'app/shared/model/current-measuring-transformer.model';
import { CurrentMeasuringTransformerService } from './current-measuring-transformer.service';
import { ICmtType } from 'app/shared/model/cmt-type.model';
import { CmtTypeService } from 'app/entities/cmt-type';
import { IManufacturer } from 'app/shared/model/manufacturer.model';
import { ManufacturerService } from 'app/entities/manufacturer';
import { ISubstation } from 'app/shared/model/substation.model';
import { SubstationService } from 'app/entities/substation';
import { IField } from 'app/shared/model/field.model';
import { FieldService } from 'app/entities/field';

@Component({
    selector: 'jhi-current-measuring-transformer-update',
    templateUrl: './current-measuring-transformer-update.component.html'
})
export class CurrentMeasuringTransformerUpdateComponent implements OnInit {
    private _currentMeasuringTransformer: ICurrentMeasuringTransformer;
    isSaving: boolean;

    cmttypes: ICmtType[];

    manufacturers: IManufacturer[];

    substations: ISubstation[];

    fields: IField[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private currentMeasuringTransformerService: CurrentMeasuringTransformerService,
        private cmtTypeService: CmtTypeService,
        private manufacturerService: ManufacturerService,
        private substationService: SubstationService,
        private fieldService: FieldService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ currentMeasuringTransformer }) => {
            this.currentMeasuringTransformer = currentMeasuringTransformer;
        });
        this.cmtTypeService.query().subscribe(
            (res: HttpResponse<ICmtType[]>) => {
                this.cmttypes = res.body;
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
        if (this.currentMeasuringTransformer.id !== undefined) {
            this.subscribeToSaveResponse(this.currentMeasuringTransformerService.update(this.currentMeasuringTransformer));
        } else {
            this.subscribeToSaveResponse(this.currentMeasuringTransformerService.create(this.currentMeasuringTransformer));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICurrentMeasuringTransformer>>) {
        result.subscribe(
            (res: HttpResponse<ICurrentMeasuringTransformer>) => this.onSaveSuccess(),
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

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackCmtTypeById(index: number, item: ICmtType) {
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
    get currentMeasuringTransformer() {
        return this._currentMeasuringTransformer;
    }

    set currentMeasuringTransformer(currentMeasuringTransformer: ICurrentMeasuringTransformer) {
        this._currentMeasuringTransformer = currentMeasuringTransformer;
    }
}
