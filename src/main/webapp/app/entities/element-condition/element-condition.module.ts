import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    ElementConditionComponent,
    ElementConditionDetailComponent,
    ElementConditionUpdateComponent,
    ElementConditionDeletePopupComponent,
    ElementConditionDeleteDialogComponent,
    elementConditionRoute,
    elementConditionPopupRoute
} from './';

const ENTITY_STATES = [...elementConditionRoute, ...elementConditionPopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ElementConditionComponent,
        ElementConditionDetailComponent,
        ElementConditionUpdateComponent,
        ElementConditionDeleteDialogComponent,
        ElementConditionDeletePopupComponent
    ],
    entryComponents: [
        ElementConditionComponent,
        ElementConditionUpdateComponent,
        ElementConditionDeleteDialogComponent,
        ElementConditionDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesElementConditionModule {}
