import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    VmtTypeComponent,
    VmtTypeDetailComponent,
    VmtTypeUpdateComponent,
    VmtTypeDeletePopupComponent,
    VmtTypeDeleteDialogComponent,
    vmtTypeRoute,
    vmtTypePopupRoute
} from './';

const ENTITY_STATES = [...vmtTypeRoute, ...vmtTypePopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        VmtTypeComponent,
        VmtTypeDetailComponent,
        VmtTypeUpdateComponent,
        VmtTypeDeleteDialogComponent,
        VmtTypeDeletePopupComponent
    ],
    entryComponents: [VmtTypeComponent, VmtTypeUpdateComponent, VmtTypeDeleteDialogComponent, VmtTypeDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesVmtTypeModule {}
