import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IAsset } from 'app/shared/model/asset.model';

@Component({
    selector: 'jhi-asset-detail',
    templateUrl: './asset-detail.component.html'
})
export class AssetDetailComponent implements OnInit {
    asset: IAsset;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ asset }) => {
            this.asset = asset;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
