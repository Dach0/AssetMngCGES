import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IVoltageMeasuringTransformer } from 'app/shared/model/voltage-measuring-transformer.model';
import { VoltageMeasuringTransformerService } from './voltage-measuring-transformer.service';
import { IVmtType } from 'app/shared/model/vmt-type.model';
import { VmtTypeService } from 'app/entities/vmt-type';
import { IManufacturer } from 'app/shared/model/manufacturer.model';
import { ManufacturerService } from 'app/entities/manufacturer';
import { ISubstation } from 'app/shared/model/substation.model';
import { SubstationService } from 'app/entities/substation';
import { IField } from 'app/shared/model/field.model';
import { FieldService } from 'app/entities/field';

@Component({
    selector: 'jhi-voltage-measuring-transformer-update',
    templateUrl: './voltage-measuring-transformer-update.component.html'
})
export class VoltageMeasuringTransformerUpdateComponent implements OnInit {
    private _voltageMeasuringTransformer: IVoltageMeasuringTransformer;
    isSaving: boolean;

    vmttypes: IVmtType[];

    manufacturers: IManufacturer[];

    substations: ISubstation[];

    fields: IField[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private voltageMeasuringTransformerService: VoltageMeasuringTransformerService,
        private vmtTypeService: VmtTypeService,
        private manufacturerService: ManufacturerService,
        private substationService: SubstationService,
        private fieldService: FieldService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ voltageMeasuringTransformer }) => {
            this.voltageMeasuringTransformer = voltageMeasuringTransformer;
        });
        this.vmtTypeService.query().subscribe(
            (res: HttpResponse<IVmtType[]>) => {
                this.vmttypes = res.body;
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
        if (this.voltageMeasuringTransformer.id !== undefined) {
            this.subscribeToSaveResponse(this.voltageMeasuringTransformerService.update(this.voltageMeasuringTransformer));
        } else {
            this.subscribeToSaveResponse(this.voltageMeasuringTransformerService.create(this.voltageMeasuringTransformer));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IVoltageMeasuringTransformer>>) {
        result.subscribe(
            (res: HttpResponse<IVoltageMeasuringTransformer>) => this.onSaveSuccess(),
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

    trackVmtTypeById(index: number, item: IVmtType) {
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
    get voltageMeasuringTransformer() {
        return this._voltageMeasuringTransformer;
    }

    set voltageMeasuringTransformer(voltageMeasuringTransformer: IVoltageMeasuringTransformer) {
        this._voltageMeasuringTransformer = voltageMeasuringTransformer;
    }
}
