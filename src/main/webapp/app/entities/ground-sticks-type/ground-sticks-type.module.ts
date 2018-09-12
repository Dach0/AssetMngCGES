import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    GroundSticksTypeComponent,
    GroundSticksTypeDetailComponent,
    GroundSticksTypeUpdateComponent,
    GroundSticksTypeDeletePopupComponent,
    GroundSticksTypeDeleteDialogComponent,
    groundSticksTypeRoute,
    groundSticksTypePopupRoute
} from './';

const ENTITY_STATES = [...groundSticksTypeRoute, ...groundSticksTypePopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        GroundSticksTypeComponent,
        GroundSticksTypeDetailComponent,
        GroundSticksTypeUpdateComponent,
        GroundSticksTypeDeleteDialogComponent,
        GroundSticksTypeDeletePopupComponent
    ],
    entryComponents: [
        GroundSticksTypeComponent,
        GroundSticksTypeUpdateComponent,
        GroundSticksTypeDeleteDialogComponent,
        GroundSticksTypeDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesGroundSticksTypeModule {}
