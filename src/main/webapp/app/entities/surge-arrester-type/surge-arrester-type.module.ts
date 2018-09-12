import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    SurgeArresterTypeComponent,
    SurgeArresterTypeDetailComponent,
    SurgeArresterTypeUpdateComponent,
    SurgeArresterTypeDeletePopupComponent,
    SurgeArresterTypeDeleteDialogComponent,
    surgeArresterTypeRoute,
    surgeArresterTypePopupRoute
} from './';

const ENTITY_STATES = [...surgeArresterTypeRoute, ...surgeArresterTypePopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SurgeArresterTypeComponent,
        SurgeArresterTypeDetailComponent,
        SurgeArresterTypeUpdateComponent,
        SurgeArresterTypeDeleteDialogComponent,
        SurgeArresterTypeDeletePopupComponent
    ],
    entryComponents: [
        SurgeArresterTypeComponent,
        SurgeArresterTypeUpdateComponent,
        SurgeArresterTypeDeleteDialogComponent,
        SurgeArresterTypeDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesSurgeArresterTypeModule {}
