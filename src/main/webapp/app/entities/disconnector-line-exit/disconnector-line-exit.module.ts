import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    DisconnectorLineExitComponent,
    DisconnectorLineExitDetailComponent,
    DisconnectorLineExitUpdateComponent,
    DisconnectorLineExitDeletePopupComponent,
    DisconnectorLineExitDeleteDialogComponent,
    disconnectorLineExitRoute,
    disconnectorLineExitPopupRoute
} from './';

const ENTITY_STATES = [...disconnectorLineExitRoute, ...disconnectorLineExitPopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DisconnectorLineExitComponent,
        DisconnectorLineExitDetailComponent,
        DisconnectorLineExitUpdateComponent,
        DisconnectorLineExitDeleteDialogComponent,
        DisconnectorLineExitDeletePopupComponent
    ],
    entryComponents: [
        DisconnectorLineExitComponent,
        DisconnectorLineExitUpdateComponent,
        DisconnectorLineExitDeleteDialogComponent,
        DisconnectorLineExitDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesDisconnectorLineExitModule {}
