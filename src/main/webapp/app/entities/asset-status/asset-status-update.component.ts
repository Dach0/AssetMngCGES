import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IAssetStatus } from 'app/shared/model/asset-status.model';
import { AssetStatusService } from './asset-status.service';

@Component({
    selector: 'jhi-asset-status-update',
    templateUrl: './asset-status-update.component.html'
})
export class AssetStatusUpdateComponent implements OnInit {
    private _assetStatus: IAssetStatus;
    isSaving: boolean;

    constructor(private assetStatusService: AssetStatusService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ assetStatus }) => {
            this.assetStatus = assetStatus;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.assetStatus.id !== undefined) {
            this.subscribeToSaveResponse(this.assetStatusService.update(this.assetStatus));
        } else {
            this.subscribeToSaveResponse(this.assetStatusService.create(this.assetStatus));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAssetStatus>>) {
        result.subscribe((res: HttpResponse<IAssetStatus>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get assetStatus() {
        return this._assetStatus;
    }

    set assetStatus(assetStatus: IAssetStatus) {
        this._assetStatus = assetStatus;
    }
}
