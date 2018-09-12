import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IGroundingSticks } from 'app/shared/model/grounding-sticks.model';
import { GroundingSticksService } from './grounding-sticks.service';
import { IGroundSticksDrive } from 'app/shared/model/ground-sticks-drive.model';
import { GroundSticksDriveService } from 'app/entities/ground-sticks-drive';
import { IGroundSticksType } from 'app/shared/model/ground-sticks-type.model';
import { GroundSticksTypeService } from 'app/entities/ground-sticks-type';
import { IManufacturer } from 'app/shared/model/manufacturer.model';
import { ManufacturerService } from 'app/entities/manufacturer';
import { ISubstation } from 'app/shared/model/substation.model';
import { SubstationService } from 'app/entities/substation';
import { IField } from 'app/shared/model/field.model';
import { FieldService } from 'app/entities/field';

@Component({
    selector: 'jhi-grounding-sticks-update',
    templateUrl: './grounding-sticks-update.component.html'
})
export class GroundingSticksUpdateComponent implements OnInit {
    private _groundingSticks: IGroundingSticks;
    isSaving: boolean;

    groundsticksdrives: IGroundSticksDrive[];

    groundstickstypes: IGroundSticksType[];

    manufacturers: IManufacturer[];

    substations: ISubstation[];

    fields: IField[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private groundingSticksService: GroundingSticksService,
        private groundSticksDriveService: GroundSticksDriveService,
        private groundSticksTypeService: GroundSticksTypeService,
        private manufacturerService: ManufacturerService,
        private substationService: SubstationService,
        private fieldService: FieldService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ groundingSticks }) => {
            this.groundingSticks = groundingSticks;
        });
        this.groundSticksDriveService.query().subscribe(
            (res: HttpResponse<IGroundSticksDrive[]>) => {
                this.groundsticksdrives = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.groundSticksTypeService.query().subscribe(
            (res: HttpResponse<IGroundSticksType[]>) => {
                this.groundstickstypes = res.body;
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
        if (this.groundingSticks.id !== undefined) {
            this.subscribeToSaveResponse(this.groundingSticksService.update(this.groundingSticks));
        } else {
            this.subscribeToSaveResponse(this.groundingSticksService.create(this.groundingSticks));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IGroundingSticks>>) {
        result.subscribe((res: HttpResponse<IGroundingSticks>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackGroundSticksDriveById(index: number, item: IGroundSticksDrive) {
        return item.id;
    }

    trackGroundSticksTypeById(index: number, item: IGroundSticksType) {
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
    get groundingSticks() {
        return this._groundingSticks;
    }

    set groundingSticks(groundingSticks: IGroundingSticks) {
        this._groundingSticks = groundingSticks;
    }
}
