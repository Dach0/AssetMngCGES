import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    TransformerTypeComponent,
    TransformerTypeDetailComponent,
    TransformerTypeUpdateComponent,
    TransformerTypeDeletePopupComponent,
    TransformerTypeDeleteDialogComponent,
    transformerTypeRoute,
    transformerTypePopupRoute
} from './';

const ENTITY_STATES = [...transformerTypeRoute, ...transformerTypePopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TransformerTypeComponent,
        TransformerTypeDetailComponent,
        TransformerTypeUpdateComponent,
        TransformerTypeDeleteDialogComponent,
        TransformerTypeDeletePopupComponent
    ],
    entryComponents: [
        TransformerTypeComponent,
        TransformerTypeUpdateComponent,
        TransformerTypeDeleteDialogComponent,
        TransformerTypeDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesTransformerTypeModule {}
