import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    PylonTypeComponent,
    PylonTypeDetailComponent,
    PylonTypeUpdateComponent,
    PylonTypeDeletePopupComponent,
    PylonTypeDeleteDialogComponent,
    pylonTypeRoute,
    pylonTypePopupRoute
} from './';

const ENTITY_STATES = [...pylonTypeRoute, ...pylonTypePopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PylonTypeComponent,
        PylonTypeDetailComponent,
        PylonTypeUpdateComponent,
        PylonTypeDeleteDialogComponent,
        PylonTypeDeletePopupComponent
    ],
    entryComponents: [PylonTypeComponent, PylonTypeUpdateComponent, PylonTypeDeleteDialogComponent, PylonTypeDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesPylonTypeModule {}
