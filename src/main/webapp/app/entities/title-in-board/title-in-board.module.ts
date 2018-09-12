import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    TitleInBoardComponent,
    TitleInBoardDetailComponent,
    TitleInBoardUpdateComponent,
    TitleInBoardDeletePopupComponent,
    TitleInBoardDeleteDialogComponent,
    titleInBoardRoute,
    titleInBoardPopupRoute
} from './';

const ENTITY_STATES = [...titleInBoardRoute, ...titleInBoardPopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TitleInBoardComponent,
        TitleInBoardDetailComponent,
        TitleInBoardUpdateComponent,
        TitleInBoardDeleteDialogComponent,
        TitleInBoardDeletePopupComponent
    ],
    entryComponents: [
        TitleInBoardComponent,
        TitleInBoardUpdateComponent,
        TitleInBoardDeleteDialogComponent,
        TitleInBoardDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesTitleInBoardModule {}
