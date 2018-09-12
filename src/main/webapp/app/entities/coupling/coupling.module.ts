import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    CouplingComponent,
    CouplingDetailComponent,
    CouplingUpdateComponent,
    CouplingDeletePopupComponent,
    CouplingDeleteDialogComponent,
    couplingRoute,
    couplingPopupRoute
} from './';

const ENTITY_STATES = [...couplingRoute, ...couplingPopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CouplingComponent,
        CouplingDetailComponent,
        CouplingUpdateComponent,
        CouplingDeleteDialogComponent,
        CouplingDeletePopupComponent
    ],
    entryComponents: [CouplingComponent, CouplingUpdateComponent, CouplingDeleteDialogComponent, CouplingDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesCouplingModule {}
