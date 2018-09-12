import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    BoardMemberComponent,
    BoardMemberDetailComponent,
    BoardMemberUpdateComponent,
    BoardMemberDeletePopupComponent,
    BoardMemberDeleteDialogComponent,
    boardMemberRoute,
    boardMemberPopupRoute
} from './';

const ENTITY_STATES = [...boardMemberRoute, ...boardMemberPopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        BoardMemberComponent,
        BoardMemberDetailComponent,
        BoardMemberUpdateComponent,
        BoardMemberDeleteDialogComponent,
        BoardMemberDeletePopupComponent
    ],
    entryComponents: [BoardMemberComponent, BoardMemberUpdateComponent, BoardMemberDeleteDialogComponent, BoardMemberDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesBoardMemberModule {}
