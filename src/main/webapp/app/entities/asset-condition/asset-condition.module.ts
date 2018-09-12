import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    AssetConditionComponent,
    AssetConditionDetailComponent,
    AssetConditionUpdateComponent,
    AssetConditionDeletePopupComponent,
    AssetConditionDeleteDialogComponent,
    assetConditionRoute,
    assetConditionPopupRoute
} from './';

const ENTITY_STATES = [...assetConditionRoute, ...assetConditionPopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AssetConditionComponent,
        AssetConditionDetailComponent,
        AssetConditionUpdateComponent,
        AssetConditionDeleteDialogComponent,
        AssetConditionDeletePopupComponent
    ],
    entryComponents: [
        AssetConditionComponent,
        AssetConditionUpdateComponent,
        AssetConditionDeleteDialogComponent,
        AssetConditionDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesAssetConditionModule {}
