import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    CorrectionFactorComponent,
    CorrectionFactorDetailComponent,
    CorrectionFactorUpdateComponent,
    CorrectionFactorDeletePopupComponent,
    CorrectionFactorDeleteDialogComponent,
    correctionFactorRoute,
    correctionFactorPopupRoute
} from './';

const ENTITY_STATES = [...correctionFactorRoute, ...correctionFactorPopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CorrectionFactorComponent,
        CorrectionFactorDetailComponent,
        CorrectionFactorUpdateComponent,
        CorrectionFactorDeleteDialogComponent,
        CorrectionFactorDeletePopupComponent
    ],
    entryComponents: [
        CorrectionFactorComponent,
        CorrectionFactorUpdateComponent,
        CorrectionFactorDeleteDialogComponent,
        CorrectionFactorDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesCorrectionFactorModule {}
