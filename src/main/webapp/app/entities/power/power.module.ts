import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    PowerComponent,
    PowerDetailComponent,
    PowerUpdateComponent,
    PowerDeletePopupComponent,
    PowerDeleteDialogComponent,
    powerRoute,
    powerPopupRoute
} from './';

const ENTITY_STATES = [...powerRoute, ...powerPopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [PowerComponent, PowerDetailComponent, PowerUpdateComponent, PowerDeleteDialogComponent, PowerDeletePopupComponent],
    entryComponents: [PowerComponent, PowerUpdateComponent, PowerDeleteDialogComponent, PowerDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesPowerModule {}
