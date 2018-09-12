import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    CmtTypeComponent,
    CmtTypeDetailComponent,
    CmtTypeUpdateComponent,
    CmtTypeDeletePopupComponent,
    CmtTypeDeleteDialogComponent,
    cmtTypeRoute,
    cmtTypePopupRoute
} from './';

const ENTITY_STATES = [...cmtTypeRoute, ...cmtTypePopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CmtTypeComponent,
        CmtTypeDetailComponent,
        CmtTypeUpdateComponent,
        CmtTypeDeleteDialogComponent,
        CmtTypeDeletePopupComponent
    ],
    entryComponents: [CmtTypeComponent, CmtTypeUpdateComponent, CmtTypeDeleteDialogComponent, CmtTypeDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesCmtTypeModule {}
