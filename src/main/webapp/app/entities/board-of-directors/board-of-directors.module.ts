import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    BoardOfDirectorsComponent,
    BoardOfDirectorsDetailComponent,
    BoardOfDirectorsUpdateComponent,
    BoardOfDirectorsDeletePopupComponent,
    BoardOfDirectorsDeleteDialogComponent,
    boardOfDirectorsRoute,
    boardOfDirectorsPopupRoute
} from './';

const ENTITY_STATES = [...boardOfDirectorsRoute, ...boardOfDirectorsPopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        BoardOfDirectorsComponent,
        BoardOfDirectorsDetailComponent,
        BoardOfDirectorsUpdateComponent,
        BoardOfDirectorsDeleteDialogComponent,
        BoardOfDirectorsDeletePopupComponent
    ],
    entryComponents: [
        BoardOfDirectorsComponent,
        BoardOfDirectorsUpdateComponent,
        BoardOfDirectorsDeleteDialogComponent,
        BoardOfDirectorsDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesBoardOfDirectorsModule {}
