import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    CircuitBreakerTypeComponent,
    CircuitBreakerTypeDetailComponent,
    CircuitBreakerTypeUpdateComponent,
    CircuitBreakerTypeDeletePopupComponent,
    CircuitBreakerTypeDeleteDialogComponent,
    circuitBreakerTypeRoute,
    circuitBreakerTypePopupRoute
} from './';

const ENTITY_STATES = [...circuitBreakerTypeRoute, ...circuitBreakerTypePopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CircuitBreakerTypeComponent,
        CircuitBreakerTypeDetailComponent,
        CircuitBreakerTypeUpdateComponent,
        CircuitBreakerTypeDeleteDialogComponent,
        CircuitBreakerTypeDeletePopupComponent
    ],
    entryComponents: [
        CircuitBreakerTypeComponent,
        CircuitBreakerTypeUpdateComponent,
        CircuitBreakerTypeDeleteDialogComponent,
        CircuitBreakerTypeDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesCircuitBreakerTypeModule {}
