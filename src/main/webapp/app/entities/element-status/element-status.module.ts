import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    ElementStatusComponent,
    ElementStatusDetailComponent,
    ElementStatusUpdateComponent,
    ElementStatusDeletePopupComponent,
    ElementStatusDeleteDialogComponent,
    elementStatusRoute,
    elementStatusPopupRoute
} from './';

const ENTITY_STATES = [...elementStatusRoute, ...elementStatusPopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ElementStatusComponent,
        ElementStatusDetailComponent,
        ElementStatusUpdateComponent,
        ElementStatusDeleteDialogComponent,
        ElementStatusDeletePopupComponent
    ],
    entryComponents: [
        ElementStatusComponent,
        ElementStatusUpdateComponent,
        ElementStatusDeleteDialogComponent,
        ElementStatusDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesElementStatusModule {}
