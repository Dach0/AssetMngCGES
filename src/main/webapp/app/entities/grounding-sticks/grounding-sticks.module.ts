import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    GroundingSticksComponent,
    GroundingSticksDetailComponent,
    GroundingSticksUpdateComponent,
    GroundingSticksDeletePopupComponent,
    GroundingSticksDeleteDialogComponent,
    groundingSticksRoute,
    groundingSticksPopupRoute
} from './';

const ENTITY_STATES = [...groundingSticksRoute, ...groundingSticksPopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        GroundingSticksComponent,
        GroundingSticksDetailComponent,
        GroundingSticksUpdateComponent,
        GroundingSticksDeleteDialogComponent,
        GroundingSticksDeletePopupComponent
    ],
    entryComponents: [
        GroundingSticksComponent,
        GroundingSticksUpdateComponent,
        GroundingSticksDeleteDialogComponent,
        GroundingSticksDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesGroundingSticksModule {}
