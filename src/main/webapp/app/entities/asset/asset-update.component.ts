import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IAsset } from 'app/shared/model/asset.model';
import { AssetService } from './asset.service';
import { IType } from 'app/shared/model/type.model';
import { TypeService } from 'app/entities/type';
import { ILocation } from 'app/shared/model/location.model';
import { LocationService } from 'app/entities/location';
import { IAssetStatus } from 'app/shared/model/asset-status.model';
import { AssetStatusService } from 'app/entities/asset-status';
import { IAssetCondition } from 'app/shared/model/asset-condition.model';
import { AssetConditionService } from 'app/entities/asset-condition';
import { IEmployee } from 'app/shared/model/employee.model';
import { EmployeeService } from 'app/entities/employee';

@Component({
    selector: 'jhi-asset-update',
    templateUrl: './asset-update.component.html'
})
export class AssetUpdateComponent implements OnInit {
    private _asset: IAsset;
    isSaving: boolean;

    types: IType[];

    locations: ILocation[];

    assetstatuses: IAssetStatus[];

    assetconditions: IAssetCondition[];

    employees: IEmployee[];
    dateOfObligationDp: any;
    purchasedDateDp: any;
    inServiceDateDp: any;
    warrentyDp: any;

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private assetService: AssetService,
        private typeService: TypeService,
        private locationService: LocationService,
        private assetStatusService: AssetStatusService,
        private assetConditionService: AssetConditionService,
        private employeeService: EmployeeService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ asset }) => {
            this.asset = asset;
        });
        this.typeService.query().subscribe(
            (res: HttpResponse<IType[]>) => {
                this.types = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.locationService.query().subscribe(
            (res: HttpResponse<ILocation[]>) => {
                this.locations = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.assetStatusService.query().subscribe(
            (res: HttpResponse<IAssetStatus[]>) => {
                this.assetstatuses = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.assetConditionService.query().subscribe(
            (res: HttpResponse<IAssetCondition[]>) => {
                this.assetconditions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.employeeService.query().subscribe(
            (res: HttpResponse<IEmployee[]>) => {
                this.employees = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.asset.id !== undefined) {
            this.subscribeToSaveResponse(this.assetService.update(this.asset));
        } else {
            this.subscribeToSaveResponse(this.assetService.create(this.asset));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAsset>>) {
        result.subscribe((res: HttpResponse<IAsset>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackTypeById(index: number, item: IType) {
        return item.id;
    }

    trackLocationById(index: number, item: ILocation) {
        return item.id;
    }

    trackAssetStatusById(index: number, item: IAssetStatus) {
        return item.id;
    }

    trackAssetConditionById(index: number, item: IAssetCondition) {
        return item.id;
    }

    trackEmployeeById(index: number, item: IEmployee) {
        return item.id;
    }
    get asset() {
        return this._asset;
    }

    set asset(asset: IAsset) {
        this._asset = asset;
    }
}
