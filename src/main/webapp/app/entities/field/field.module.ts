import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    FieldComponent,
    FieldDetailComponent,
    FieldUpdateComponent,
    FieldDeletePopupComponent,
    FieldDeleteDialogComponent,
    fieldRoute,
    fieldPopupRoute
} from './';

const ENTITY_STATES = [...fieldRoute, ...fieldPopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [FieldComponent, FieldDetailComponent, FieldUpdateComponent, FieldDeleteDialogComponent, FieldDeletePopupComponent],
    entryComponents: [FieldComponent, FieldUpdateComponent, FieldDeleteDialogComponent, FieldDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesFieldModule {}
