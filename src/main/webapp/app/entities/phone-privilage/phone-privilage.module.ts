import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    PhonePrivilageComponent,
    PhonePrivilageDetailComponent,
    PhonePrivilageUpdateComponent,
    PhonePrivilageDeletePopupComponent,
    PhonePrivilageDeleteDialogComponent,
    phonePrivilageRoute,
    phonePrivilagePopupRoute
} from './';

const ENTITY_STATES = [...phonePrivilageRoute, ...phonePrivilagePopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PhonePrivilageComponent,
        PhonePrivilageDetailComponent,
        PhonePrivilageUpdateComponent,
        PhonePrivilageDeleteDialogComponent,
        PhonePrivilageDeletePopupComponent
    ],
    entryComponents: [
        PhonePrivilageComponent,
        PhonePrivilageUpdateComponent,
        PhonePrivilageDeleteDialogComponent,
        PhonePrivilageDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesPhonePrivilageModule {}
