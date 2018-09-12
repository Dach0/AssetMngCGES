import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    VoltageLevelComponent,
    VoltageLevelDetailComponent,
    VoltageLevelUpdateComponent,
    VoltageLevelDeletePopupComponent,
    VoltageLevelDeleteDialogComponent,
    voltageLevelRoute,
    voltageLevelPopupRoute
} from './';

const ENTITY_STATES = [...voltageLevelRoute, ...voltageLevelPopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        VoltageLevelComponent,
        VoltageLevelDetailComponent,
        VoltageLevelUpdateComponent,
        VoltageLevelDeleteDialogComponent,
        VoltageLevelDeletePopupComponent
    ],
    entryComponents: [
        VoltageLevelComponent,
        VoltageLevelUpdateComponent,
        VoltageLevelDeleteDialogComponent,
        VoltageLevelDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesVoltageLevelModule {}
