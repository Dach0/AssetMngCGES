import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    EarthWireCrossSectComponent,
    EarthWireCrossSectDetailComponent,
    EarthWireCrossSectUpdateComponent,
    EarthWireCrossSectDeletePopupComponent,
    EarthWireCrossSectDeleteDialogComponent,
    earthWireCrossSectRoute,
    earthWireCrossSectPopupRoute
} from './';

const ENTITY_STATES = [...earthWireCrossSectRoute, ...earthWireCrossSectPopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EarthWireCrossSectComponent,
        EarthWireCrossSectDetailComponent,
        EarthWireCrossSectUpdateComponent,
        EarthWireCrossSectDeleteDialogComponent,
        EarthWireCrossSectDeletePopupComponent
    ],
    entryComponents: [
        EarthWireCrossSectComponent,
        EarthWireCrossSectUpdateComponent,
        EarthWireCrossSectDeleteDialogComponent,
        EarthWireCrossSectDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesEarthWireCrossSectModule {}
