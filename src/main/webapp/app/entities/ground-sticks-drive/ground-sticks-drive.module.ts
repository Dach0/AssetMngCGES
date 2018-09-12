import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    GroundSticksDriveComponent,
    GroundSticksDriveDetailComponent,
    GroundSticksDriveUpdateComponent,
    GroundSticksDriveDeletePopupComponent,
    GroundSticksDriveDeleteDialogComponent,
    groundSticksDriveRoute,
    groundSticksDrivePopupRoute
} from './';

const ENTITY_STATES = [...groundSticksDriveRoute, ...groundSticksDrivePopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        GroundSticksDriveComponent,
        GroundSticksDriveDetailComponent,
        GroundSticksDriveUpdateComponent,
        GroundSticksDriveDeleteDialogComponent,
        GroundSticksDriveDeletePopupComponent
    ],
    entryComponents: [
        GroundSticksDriveComponent,
        GroundSticksDriveUpdateComponent,
        GroundSticksDriveDeleteDialogComponent,
        GroundSticksDriveDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesGroundSticksDriveModule {}
