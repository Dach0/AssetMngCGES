import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IAssetCondition } from 'app/shared/model/asset-condition.model';
import { AssetConditionService } from './asset-condition.service';

@Component({
    selector: 'jhi-asset-condition-update',
    templateUrl: './asset-condition-update.component.html'
})
export class AssetConditionUpdateComponent implements OnInit {
    private _assetCondition: IAssetCondition;
    isSaving: boolean;

    constructor(private assetConditionService: AssetConditionService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ assetCondition }) => {
            this.assetCondition = assetCondition;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.assetCondition.id !== undefined) {
            this.subscribeToSaveResponse(this.assetConditionService.update(this.assetCondition));
        } else {
            this.subscribeToSaveResponse(this.assetConditionService.create(this.assetCondition));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAssetCondition>>) {
        result.subscribe((res: HttpResponse<IAssetCondition>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get assetCondition() {
        return this._assetCondition;
    }

    set assetCondition(assetCondition: IAssetCondition) {
        this._assetCondition = assetCondition;
    }
}
