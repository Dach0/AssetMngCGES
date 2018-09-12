import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    CircuitBreakerDriveComponent,
    CircuitBreakerDriveDetailComponent,
    CircuitBreakerDriveUpdateComponent,
    CircuitBreakerDriveDeletePopupComponent,
    CircuitBreakerDriveDeleteDialogComponent,
    circuitBreakerDriveRoute,
    circuitBreakerDrivePopupRoute
} from './';

const ENTITY_STATES = [...circuitBreakerDriveRoute, ...circuitBreakerDrivePopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CircuitBreakerDriveComponent,
        CircuitBreakerDriveDetailComponent,
        CircuitBreakerDriveUpdateComponent,
        CircuitBreakerDriveDeleteDialogComponent,
        CircuitBreakerDriveDeletePopupComponent
    ],
    entryComponents: [
        CircuitBreakerDriveComponent,
        CircuitBreakerDriveUpdateComponent,
        CircuitBreakerDriveDeleteDialogComponent,
        CircuitBreakerDriveDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesCircuitBreakerDriveModule {}
