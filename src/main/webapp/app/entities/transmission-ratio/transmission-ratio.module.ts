import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    TransmissionRatioComponent,
    TransmissionRatioDetailComponent,
    TransmissionRatioUpdateComponent,
    TransmissionRatioDeletePopupComponent,
    TransmissionRatioDeleteDialogComponent,
    transmissionRatioRoute,
    transmissionRatioPopupRoute
} from './';

const ENTITY_STATES = [...transmissionRatioRoute, ...transmissionRatioPopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TransmissionRatioComponent,
        TransmissionRatioDetailComponent,
        TransmissionRatioUpdateComponent,
        TransmissionRatioDeleteDialogComponent,
        TransmissionRatioDeletePopupComponent
    ],
    entryComponents: [
        TransmissionRatioComponent,
        TransmissionRatioUpdateComponent,
        TransmissionRatioDeleteDialogComponent,
        TransmissionRatioDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesTransmissionRatioModule {}
