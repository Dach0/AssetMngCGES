import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    ConductorCrossSectComponent,
    ConductorCrossSectDetailComponent,
    ConductorCrossSectUpdateComponent,
    ConductorCrossSectDeletePopupComponent,
    ConductorCrossSectDeleteDialogComponent,
    conductorCrossSectRoute,
    conductorCrossSectPopupRoute
} from './';

const ENTITY_STATES = [...conductorCrossSectRoute, ...conductorCrossSectPopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ConductorCrossSectComponent,
        ConductorCrossSectDetailComponent,
        ConductorCrossSectUpdateComponent,
        ConductorCrossSectDeleteDialogComponent,
        ConductorCrossSectDeletePopupComponent
    ],
    entryComponents: [
        ConductorCrossSectComponent,
        ConductorCrossSectUpdateComponent,
        ConductorCrossSectDeleteDialogComponent,
        ConductorCrossSectDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesConductorCrossSectModule {}
