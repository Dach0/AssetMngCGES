import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    JobStatusComponent,
    JobStatusDetailComponent,
    JobStatusUpdateComponent,
    JobStatusDeletePopupComponent,
    JobStatusDeleteDialogComponent,
    jobStatusRoute,
    jobStatusPopupRoute
} from './';

const ENTITY_STATES = [...jobStatusRoute, ...jobStatusPopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        JobStatusComponent,
        JobStatusDetailComponent,
        JobStatusUpdateComponent,
        JobStatusDeleteDialogComponent,
        JobStatusDeletePopupComponent
    ],
    entryComponents: [JobStatusComponent, JobStatusUpdateComponent, JobStatusDeleteDialogComponent, JobStatusDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesJobStatusModule {}
