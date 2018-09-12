import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    AssetStatusComponent,
    AssetStatusDetailComponent,
    AssetStatusUpdateComponent,
    AssetStatusDeletePopupComponent,
    AssetStatusDeleteDialogComponent,
    assetStatusRoute,
    assetStatusPopupRoute
} from './';

const ENTITY_STATES = [...assetStatusRoute, ...assetStatusPopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AssetStatusComponent,
        AssetStatusDetailComponent,
        AssetStatusUpdateComponent,
        AssetStatusDeleteDialogComponent,
        AssetStatusDeletePopupComponent
    ],
    entryComponents: [AssetStatusComponent, AssetStatusUpdateComponent, AssetStatusDeleteDialogComponent, AssetStatusDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesAssetStatusModule {}
