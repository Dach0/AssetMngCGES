import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IDisconnectorLineExit } from 'app/shared/model/disconnector-line-exit.model';
import { DisconnectorLineExitService } from './disconnector-line-exit.service';
import { IDisconnectorDrive } from 'app/shared/model/disconnector-drive.model';
import { DisconnectorDriveService } from 'app/entities/disconnector-drive';
import { IDisconnectorType } from 'app/shared/model/disconnector-type.model';
import { DisconnectorTypeService } from 'app/entities/disconnector-type';
import { IManufacturer } from 'app/shared/model/manufacturer.model';
import { ManufacturerService } from 'app/entities/manufacturer';
import { ISubstation } from 'app/shared/model/substation.model';
import { SubstationService } from 'app/entities/substation';
import { IField } from 'app/shared/model/field.model';
import { FieldService } from 'app/entities/field';

@Component({
    selector: 'jhi-disconnector-line-exit-update',
    templateUrl: './disconnector-line-exit-update.component.html'
})
export class DisconnectorLineExitUpdateComponent implements OnInit {
    private _disconnectorLineExit: IDisconnectorLineExit;
    isSaving: boolean;

    disconnectordrives: IDisconnectorDrive[];

    disconnectortypes: IDisconnectorType[];

    manufacturers: IManufacturer[];

    substations: ISubstation[];

    fields: IField[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private disconnectorLineExitService: DisconnectorLineExitService,
        private disconnectorDriveService: DisconnectorDriveService,
        private disconnectorTypeService: DisconnectorTypeService,
        private manufacturerService: ManufacturerService,
        private substationService: SubstationService,
        private fieldService: FieldService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ disconnectorLineExit }) => {
            this.disconnectorLineExit = disconnectorLineExit;
        });
        this.disconnectorDriveService.query().subscribe(
            (res: HttpResponse<IDisconnectorDrive[]>) => {
                this.disconnectordrives = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.disconnectorTypeService.query().subscribe(
            (res: HttpResponse<IDisconnectorType[]>) => {
                this.disconnectortypes = res.body;
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
        if (this.disconnectorLineExit.id !== undefined) {
            this.subscribeToSaveResponse(this.disconnectorLineExitService.update(this.disconnectorLineExit));
        } else {
            this.subscribeToSaveResponse(this.disconnectorLineExitService.create(this.disconnectorLineExit));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDisconnectorLineExit>>) {
        result.subscribe(
            (res: HttpResponse<IDisconnectorLineExit>) => this.onSaveSuccess(),
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

    trackDisconnectorDriveById(index: number, item: IDisconnectorDrive) {
        return item.id;
    }

    trackDisconnectorTypeById(index: number, item: IDisconnectorType) {
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
    get disconnectorLineExit() {
        return this._disconnectorLineExit;
    }

    set disconnectorLineExit(disconnectorLineExit: IDisconnectorLineExit) {
        this._disconnectorLineExit = disconnectorLineExit;
    }
}
