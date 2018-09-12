import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    SubstationComponent,
    SubstationDetailComponent,
    SubstationUpdateComponent,
    SubstationDeletePopupComponent,
    SubstationDeleteDialogComponent,
    substationRoute,
    substationPopupRoute
} from './';

const ENTITY_STATES = [...substationRoute, ...substationPopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SubstationComponent,
        SubstationDetailComponent,
        SubstationUpdateComponent,
        SubstationDeleteDialogComponent,
        SubstationDeletePopupComponent
    ],
    entryComponents: [SubstationComponent, SubstationUpdateComponent, SubstationDeleteDialogComponent, SubstationDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesSubstationModule {}
