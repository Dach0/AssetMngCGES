import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    TransformerComponent,
    TransformerDetailComponent,
    TransformerUpdateComponent,
    TransformerDeletePopupComponent,
    TransformerDeleteDialogComponent,
    transformerRoute,
    transformerPopupRoute
} from './';

const ENTITY_STATES = [...transformerRoute, ...transformerPopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TransformerComponent,
        TransformerDetailComponent,
        TransformerUpdateComponent,
        TransformerDeleteDialogComponent,
        TransformerDeletePopupComponent
    ],
    entryComponents: [TransformerComponent, TransformerUpdateComponent, TransformerDeleteDialogComponent, TransformerDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesTransformerModule {}
