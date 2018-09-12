import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IDisconnectorBusBar } from 'app/shared/model/disconnector-bus-bar.model';
import { DisconnectorBusBarService } from './disconnector-bus-bar.service';
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
    selector: 'jhi-disconnector-bus-bar-update',
    templateUrl: './disconnector-bus-bar-update.component.html'
})
export class DisconnectorBusBarUpdateComponent implements OnInit {
    private _disconnectorBusBar: IDisconnectorBusBar;
    isSaving: boolean;

    disconnectordrives: IDisconnectorDrive[];

    disconnectortypes: IDisconnectorType[];

    manufacturers: IManufacturer[];

    substations: ISubstation[];

    fields: IField[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private disconnectorBusBarService: DisconnectorBusBarService,
        private disconnectorDriveService: DisconnectorDriveService,
        private disconnectorTypeService: DisconnectorTypeService,
        private manufacturerService: ManufacturerService,
        private substationService: SubstationService,
        private fieldService: FieldService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ disconnectorBusBar }) => {
            this.disconnectorBusBar = disconnectorBusBar;
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
        if (this.disconnectorBusBar.id !== undefined) {
            this.subscribeToSaveResponse(this.disconnectorBusBarService.update(this.disconnectorBusBar));
        } else {
            this.subscribeToSaveResponse(this.disconnectorBusBarService.create(this.disconnectorBusBar));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDisconnectorBusBar>>) {
        result.subscribe((res: HttpResponse<IDisconnectorBusBar>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get disconnectorBusBar() {
        return this._disconnectorBusBar;
    }

    set disconnectorBusBar(disconnectorBusBar: IDisconnectorBusBar) {
        this._disconnectorBusBar = disconnectorBusBar;
    }
}
