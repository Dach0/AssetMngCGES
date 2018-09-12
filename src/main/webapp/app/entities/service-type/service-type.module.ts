import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    ServiceTypeComponent,
    ServiceTypeDetailComponent,
    ServiceTypeUpdateComponent,
    ServiceTypeDeletePopupComponent,
    ServiceTypeDeleteDialogComponent,
    serviceTypeRoute,
    serviceTypePopupRoute
} from './';

const ENTITY_STATES = [...serviceTypeRoute, ...serviceTypePopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ServiceTypeComponent,
        ServiceTypeDetailComponent,
        ServiceTypeUpdateComponent,
        ServiceTypeDeleteDialogComponent,
        ServiceTypeDeletePopupComponent
    ],
    entryComponents: [ServiceTypeComponent, ServiceTypeUpdateComponent, ServiceTypeDeleteDialogComponent, ServiceTypeDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesServiceTypeModule {}
