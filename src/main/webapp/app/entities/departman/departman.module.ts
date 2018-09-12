import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    DepartmanComponent,
    DepartmanDetailComponent,
    DepartmanUpdateComponent,
    DepartmanDeletePopupComponent,
    DepartmanDeleteDialogComponent,
    departmanRoute,
    departmanPopupRoute
} from './';

const ENTITY_STATES = [...departmanRoute, ...departmanPopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DepartmanComponent,
        DepartmanDetailComponent,
        DepartmanUpdateComponent,
        DepartmanDeleteDialogComponent,
        DepartmanDeletePopupComponent
    ],
    entryComponents: [DepartmanComponent, DepartmanUpdateComponent, DepartmanDeleteDialogComponent, DepartmanDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesDepartmanModule {}
