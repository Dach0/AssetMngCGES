import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    ThermalLimitComponent,
    ThermalLimitDetailComponent,
    ThermalLimitUpdateComponent,
    ThermalLimitDeletePopupComponent,
    ThermalLimitDeleteDialogComponent,
    thermalLimitRoute,
    thermalLimitPopupRoute
} from './';

const ENTITY_STATES = [...thermalLimitRoute, ...thermalLimitPopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ThermalLimitComponent,
        ThermalLimitDetailComponent,
        ThermalLimitUpdateComponent,
        ThermalLimitDeleteDialogComponent,
        ThermalLimitDeletePopupComponent
    ],
    entryComponents: [
        ThermalLimitComponent,
        ThermalLimitUpdateComponent,
        ThermalLimitDeleteDialogComponent,
        ThermalLimitDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesThermalLimitModule {}
