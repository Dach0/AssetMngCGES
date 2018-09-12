import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    EmployeeGroupComponent,
    EmployeeGroupDetailComponent,
    EmployeeGroupUpdateComponent,
    EmployeeGroupDeletePopupComponent,
    EmployeeGroupDeleteDialogComponent,
    employeeGroupRoute,
    employeeGroupPopupRoute
} from './';

const ENTITY_STATES = [...employeeGroupRoute, ...employeeGroupPopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EmployeeGroupComponent,
        EmployeeGroupDetailComponent,
        EmployeeGroupUpdateComponent,
        EmployeeGroupDeleteDialogComponent,
        EmployeeGroupDeletePopupComponent
    ],
    entryComponents: [
        EmployeeGroupComponent,
        EmployeeGroupUpdateComponent,
        EmployeeGroupDeleteDialogComponent,
        EmployeeGroupDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesEmployeeGroupModule {}
