import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    DisconnectorDriveComponent,
    DisconnectorDriveDetailComponent,
    DisconnectorDriveUpdateComponent,
    DisconnectorDriveDeletePopupComponent,
    DisconnectorDriveDeleteDialogComponent,
    disconnectorDriveRoute,
    disconnectorDrivePopupRoute
} from './';

const ENTITY_STATES = [...disconnectorDriveRoute, ...disconnectorDrivePopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DisconnectorDriveComponent,
        DisconnectorDriveDetailComponent,
        DisconnectorDriveUpdateComponent,
        DisconnectorDriveDeleteDialogComponent,
        DisconnectorDriveDeletePopupComponent
    ],
    entryComponents: [
        DisconnectorDriveComponent,
        DisconnectorDriveUpdateComponent,
        DisconnectorDriveDeleteDialogComponent,
        DisconnectorDriveDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesDisconnectorDriveModule {}
