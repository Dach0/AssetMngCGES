import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    DisconnectorBusBarComponent,
    DisconnectorBusBarDetailComponent,
    DisconnectorBusBarUpdateComponent,
    DisconnectorBusBarDeletePopupComponent,
    DisconnectorBusBarDeleteDialogComponent,
    disconnectorBusBarRoute,
    disconnectorBusBarPopupRoute
} from './';

const ENTITY_STATES = [...disconnectorBusBarRoute, ...disconnectorBusBarPopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DisconnectorBusBarComponent,
        DisconnectorBusBarDetailComponent,
        DisconnectorBusBarUpdateComponent,
        DisconnectorBusBarDeleteDialogComponent,
        DisconnectorBusBarDeletePopupComponent
    ],
    entryComponents: [
        DisconnectorBusBarComponent,
        DisconnectorBusBarUpdateComponent,
        DisconnectorBusBarDeleteDialogComponent,
        DisconnectorBusBarDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesDisconnectorBusBarModule {}
