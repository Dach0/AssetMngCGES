import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    CircuitBreakerComponent,
    CircuitBreakerDetailComponent,
    CircuitBreakerUpdateComponent,
    CircuitBreakerDeletePopupComponent,
    CircuitBreakerDeleteDialogComponent,
    circuitBreakerRoute,
    circuitBreakerPopupRoute
} from './';

const ENTITY_STATES = [...circuitBreakerRoute, ...circuitBreakerPopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CircuitBreakerComponent,
        CircuitBreakerDetailComponent,
        CircuitBreakerUpdateComponent,
        CircuitBreakerDeleteDialogComponent,
        CircuitBreakerDeletePopupComponent
    ],
    entryComponents: [
        CircuitBreakerComponent,
        CircuitBreakerUpdateComponent,
        CircuitBreakerDeleteDialogComponent,
        CircuitBreakerDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesCircuitBreakerModule {}
