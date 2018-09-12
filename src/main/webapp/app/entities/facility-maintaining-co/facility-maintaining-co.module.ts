import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    FacilityMaintainingCoComponent,
    FacilityMaintainingCoDetailComponent,
    FacilityMaintainingCoUpdateComponent,
    FacilityMaintainingCoDeletePopupComponent,
    FacilityMaintainingCoDeleteDialogComponent,
    facilityMaintainingCoRoute,
    facilityMaintainingCoPopupRoute
} from './';

const ENTITY_STATES = [...facilityMaintainingCoRoute, ...facilityMaintainingCoPopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        FacilityMaintainingCoComponent,
        FacilityMaintainingCoDetailComponent,
        FacilityMaintainingCoUpdateComponent,
        FacilityMaintainingCoDeleteDialogComponent,
        FacilityMaintainingCoDeletePopupComponent
    ],
    entryComponents: [
        FacilityMaintainingCoComponent,
        FacilityMaintainingCoUpdateComponent,
        FacilityMaintainingCoDeleteDialogComponent,
        FacilityMaintainingCoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesFacilityMaintainingCoModule {}
