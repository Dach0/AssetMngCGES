import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssetMenagementCgesSharedModule } from 'app/shared';
import {
    OhlComponent,
    OhlDetailComponent,
    OhlUpdateComponent,
    OhlDeletePopupComponent,
    OhlDeleteDialogComponent,
    ohlRoute,
    ohlPopupRoute
} from './';

const ENTITY_STATES = [...ohlRoute, ...ohlPopupRoute];

@NgModule({
    imports: [AssetMenagementCgesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [OhlComponent, OhlDetailComponent, OhlUpdateComponent, OhlDeleteDialogComponent, OhlDeletePopupComponent],
    entryComponents: [OhlComponent, OhlUpdateComponent, OhlDeleteDialogComponent, OhlDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesOhlModule {}
