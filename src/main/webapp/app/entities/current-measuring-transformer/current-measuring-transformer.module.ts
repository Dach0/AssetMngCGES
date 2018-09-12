import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    CurrentMeasuringTransformerComponent,
    CurrentMeasuringTransformerDetailComponent,
    CurrentMeasuringTransformerUpdateComponent,
    CurrentMeasuringTransformerDeletePopupComponent,
    CurrentMeasuringTransformerDeleteDialogComponent,
    currentMeasuringTransformerRoute,
    currentMeasuringTransformerPopupRoute
} from './';

const ENTITY_STATES = [...currentMeasuringTransformerRoute, ...currentMeasuringTransformerPopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CurrentMeasuringTransformerComponent,
        CurrentMeasuringTransformerDetailComponent,
        CurrentMeasuringTransformerUpdateComponent,
        CurrentMeasuringTransformerDeleteDialogComponent,
        CurrentMeasuringTransformerDeletePopupComponent
    ],
    entryComponents: [
        CurrentMeasuringTransformerComponent,
        CurrentMeasuringTransformerUpdateComponent,
        CurrentMeasuringTransformerDeleteDialogComponent,
        CurrentMeasuringTransformerDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesCurrentMeasuringTransformerModule {}
