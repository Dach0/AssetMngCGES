import { NgModule } from '@angular/core';

import { AssetMenagementCgesSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [AssetMenagementCgesSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [AssetMenagementCgesSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class AssetMenagementCgesSharedCommonModule {}
