import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAssetCondition } from 'app/shared/model/asset-condition.model';

@Component({
    selector: 'jhi-asset-condition-detail',
    templateUrl: './asset-condition-detail.component.html'
})
export class AssetConditionDetailComponent implements OnInit {
    assetCondition: IAssetCondition;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ assetCondition }) => {
            this.assetCondition = assetCondition;
        });
    }

    previousState() {
        window.history.back();
    }
}
