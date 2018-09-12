import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAssetStatus } from 'app/shared/model/asset-status.model';

@Component({
    selector: 'jhi-asset-status-detail',
    templateUrl: './asset-status-detail.component.html'
})
export class AssetStatusDetailComponent implements OnInit {
    assetStatus: IAssetStatus;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ assetStatus }) => {
            this.assetStatus = assetStatus;
        });
    }

    previousState() {
        window.history.back();
    }
}
