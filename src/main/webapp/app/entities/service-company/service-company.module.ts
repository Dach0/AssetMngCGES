import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    ServiceCompanyComponent,
    ServiceCompanyDetailComponent,
    ServiceCompanyUpdateComponent,
    ServiceCompanyDeletePopupComponent,
    ServiceCompanyDeleteDialogComponent,
    serviceCompanyRoute,
    serviceCompanyPopupRoute
} from './';

const ENTITY_STATES = [...serviceCompanyRoute, ...serviceCompanyPopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ServiceCompanyComponent,
        ServiceCompanyDetailComponent,
        ServiceCompanyUpdateComponent,
        ServiceCompanyDeleteDialogComponent,
        ServiceCompanyDeletePopupComponent
    ],
    entryComponents: [
        ServiceCompanyComponent,
        ServiceCompanyUpdateComponent,
        ServiceCompanyDeleteDialogComponent,
        ServiceCompanyDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesServiceCompanyModule {}
