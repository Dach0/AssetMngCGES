import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    TransformatorNumberComponent,
    TransformatorNumberDetailComponent,
    TransformatorNumberUpdateComponent,
    TransformatorNumberDeletePopupComponent,
    TransformatorNumberDeleteDialogComponent,
    transformatorNumberRoute,
    transformatorNumberPopupRoute
} from './';

const ENTITY_STATES = [...transformatorNumberRoute, ...transformatorNumberPopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TransformatorNumberComponent,
        TransformatorNumberDetailComponent,
        TransformatorNumberUpdateComponent,
        TransformatorNumberDeleteDialogComponent,
        TransformatorNumberDeletePopupComponent
    ],
    entryComponents: [
        TransformatorNumberComponent,
        TransformatorNumberUpdateComponent,
        TransformatorNumberDeleteDialogComponent,
        TransformatorNumberDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesTransformatorNumberModule {}
