import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    VoltageMeasuringTransformerComponent,
    VoltageMeasuringTransformerDetailComponent,
    VoltageMeasuringTransformerUpdateComponent,
    VoltageMeasuringTransformerDeletePopupComponent,
    VoltageMeasuringTransformerDeleteDialogComponent,
    voltageMeasuringTransformerRoute,
    voltageMeasuringTransformerPopupRoute
} from './';

const ENTITY_STATES = [...voltageMeasuringTransformerRoute, ...voltageMeasuringTransformerPopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        VoltageMeasuringTransformerComponent,
        VoltageMeasuringTransformerDetailComponent,
        VoltageMeasuringTransformerUpdateComponent,
        VoltageMeasuringTransformerDeleteDialogComponent,
        VoltageMeasuringTransformerDeletePopupComponent
    ],
    entryComponents: [
        VoltageMeasuringTransformerComponent,
        VoltageMeasuringTransformerUpdateComponent,
        VoltageMeasuringTransformerDeleteDialogComponent,
        VoltageMeasuringTransformerDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesVoltageMeasuringTransformerModule {}
