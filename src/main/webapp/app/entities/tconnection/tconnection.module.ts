import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    TconnectionComponent,
    TconnectionDetailComponent,
    TconnectionUpdateComponent,
    TconnectionDeletePopupComponent,
    TconnectionDeleteDialogComponent,
    tconnectionRoute,
    tconnectionPopupRoute
} from './';

const ENTITY_STATES = [...tconnectionRoute, ...tconnectionPopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TconnectionComponent,
        TconnectionDetailComponent,
        TconnectionUpdateComponent,
        TconnectionDeleteDialogComponent,
        TconnectionDeletePopupComponent
    ],
    entryComponents: [TconnectionComponent, TconnectionUpdateComponent, TconnectionDeleteDialogComponent, TconnectionDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesTconnectionModule {}
