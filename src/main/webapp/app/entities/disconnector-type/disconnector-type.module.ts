import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    DisconnectorTypeComponent,
    DisconnectorTypeDetailComponent,
    DisconnectorTypeUpdateComponent,
    DisconnectorTypeDeletePopupComponent,
    DisconnectorTypeDeleteDialogComponent,
    disconnectorTypeRoute,
    disconnectorTypePopupRoute
} from './';

const ENTITY_STATES = [...disconnectorTypeRoute, ...disconnectorTypePopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DisconnectorTypeComponent,
        DisconnectorTypeDetailComponent,
        DisconnectorTypeUpdateComponent,
        DisconnectorTypeDeleteDialogComponent,
        DisconnectorTypeDeletePopupComponent
    ],
    entryComponents: [
        DisconnectorTypeComponent,
        DisconnectorTypeUpdateComponent,
        DisconnectorTypeDeleteDialogComponent,
        DisconnectorTypeDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesDisconnectorTypeModule {}
