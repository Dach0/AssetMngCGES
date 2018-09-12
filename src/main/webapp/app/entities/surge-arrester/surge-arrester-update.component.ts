import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ISurgeArrester } from 'app/shared/model/surge-arrester.model';
import { SurgeArresterService } from './surge-arrester.service';
import { ISurgeArresterType } from 'app/shared/model/surge-arrester-type.model';
import { SurgeArresterTypeService } from 'app/entities/surge-arrester-type';
import { IManufacturer } from 'app/shared/model/manufacturer.model';
import { ManufacturerService } from 'app/entities/manufacturer';
import { ISubstation } from 'app/shared/model/substation.model';
import { SubstationService } from 'app/entities/substation';
import { IField } from 'app/shared/model/field.model';
import { FieldService } from 'app/entities/field';

@Component({
    selector: 'jhi-surge-arrester-update',
    templateUrl: './surge-arrester-update.component.html'
})
export class SurgeArresterUpdateComponent implements OnInit {
    private _surgeArrester: ISurgeArrester;
    isSaving: boolean;

    surgearrestertypes: ISurgeArresterType[];

    manufacturers: IManufacturer[];

    substations: ISubstation[];

    fields: IField[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private surgeArresterService: SurgeArresterService,
        private surgeArresterTypeService: SurgeArresterTypeService,
        private manufacturerService: ManufacturerService,
        private substationService: SubstationService,
        private fieldService: FieldService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ surgeArrester }) => {
            this.surgeArrester = surgeArrester;
        });
        this.surgeArresterTypeService.query().subscribe(
            (res: HttpResponse<ISurgeArresterType[]>) => {
                this.surgearrestertypes = res.body;
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
        if (this.surgeArrester.id !== undefined) {
            this.subscribeToSaveResponse(this.surgeArresterService.update(this.surgeArrester));
        } else {
            this.subscribeToSaveResponse(this.surgeArresterService.create(this.surgeArrester));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISurgeArrester>>) {
        result.subscribe((res: HttpResponse<ISurgeArrester>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackSurgeArresterTypeById(index: number, item: ISurgeArresterType) {
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
    get surgeArrester() {
        return this._surgeArrester;
    }

    set surgeArrester(surgeArrester: ISurgeArrester) {
        this._surgeArrester = surgeArrester;
    }
}
