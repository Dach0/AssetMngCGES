import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    QualificationComponent,
    QualificationDetailComponent,
    QualificationUpdateComponent,
    QualificationDeletePopupComponent,
    QualificationDeleteDialogComponent,
    qualificationRoute,
    qualificationPopupRoute
} from './';

const ENTITY_STATES = [...qualificationRoute, ...qualificationPopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        QualificationComponent,
        QualificationDetailComponent,
        QualificationUpdateComponent,
        QualificationDeleteDialogComponent,
        QualificationDeletePopupComponent
    ],
    entryComponents: [
        QualificationComponent,
        QualificationUpdateComponent,
        QualificationDeleteDialogComponent,
        QualificationDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesQualificationModule {}
