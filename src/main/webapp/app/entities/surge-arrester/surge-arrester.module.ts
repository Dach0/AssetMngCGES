import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    SurgeArresterComponent,
    SurgeArresterDetailComponent,
    SurgeArresterUpdateComponent,
    SurgeArresterDeletePopupComponent,
    SurgeArresterDeleteDialogComponent,
    surgeArresterRoute,
    surgeArresterPopupRoute
} from './';

const ENTITY_STATES = [...surgeArresterRoute, ...surgeArresterPopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SurgeArresterComponent,
        SurgeArresterDetailComponent,
        SurgeArresterUpdateComponent,
        SurgeArresterDeleteDialogComponent,
        SurgeArresterDeletePopupComponent
    ],
    entryComponents: [
        SurgeArresterComponent,
        SurgeArresterUpdateComponent,
        SurgeArresterDeleteDialogComponent,
        SurgeArresterDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesSurgeArresterModule {}
