import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { AssetMenagementCgesOhlModule } from './ohl/ohl.module';
import { AssetMenagementCgesVoltageLevelModule } from './voltage-level/voltage-level.module';
import { AssetMenagementCgesConductorCrossSectModule } from './conductor-cross-sect/conductor-cross-sect.module';
import { AssetMenagementCgesEarthWireCrossSectModule } from './earth-wire-cross-sect/earth-wire-cross-sect.module';
import { AssetMenagementCgesTconnectionModule } from './tconnection/tconnection.module';
import { AssetMenagementCgesPylonTypeModule } from './pylon-type/pylon-type.module';
import { AssetMenagementCgesCorrectionFactorModule } from './correction-factor/correction-factor.module';
import { AssetMenagementCgesThermalLimitModule } from './thermal-limit/thermal-limit.module';
import { AssetMenagementCgesSubstationModule } from './substation/substation.module';
import { AssetMenagementCgesFacilityModule } from './facility/facility.module';
import { AssetMenagementCgesFacilityMaintainingCoModule } from './facility-maintaining-co/facility-maintaining-co.module';
import { AssetMenagementCgesTransformerModule } from './transformer/transformer.module';
import { AssetMenagementCgesTransformerTypeModule } from './transformer-type/transformer-type.module';
import { AssetMenagementCgesTransformatorNumberModule } from './transformator-number/transformator-number.module';
import { AssetMenagementCgesManufacturerModule } from './manufacturer/manufacturer.module';
import { AssetMenagementCgesPowerModule } from './power/power.module';
import { AssetMenagementCgesTransmissionRatioModule } from './transmission-ratio/transmission-ratio.module';
import { AssetMenagementCgesCouplingModule } from './coupling/coupling.module';
import { AssetMenagementCgesElementStatusModule } from './element-status/element-status.module';
import { AssetMenagementCgesElementConditionModule } from './element-condition/element-condition.module';
import { AssetMenagementCgesFieldModule } from './field/field.module';
import { AssetMenagementCgesDisconnectorBusBarModule } from './disconnector-bus-bar/disconnector-bus-bar.module';
import { AssetMenagementCgesDisconnectorLineExitModule } from './disconnector-line-exit/disconnector-line-exit.module';
import { AssetMenagementCgesDisconnectorTypeModule } from './disconnector-type/disconnector-type.module';
import { AssetMenagementCgesDisconnectorDriveModule } from './disconnector-drive/disconnector-drive.module';
import { AssetMenagementCgesCircuitBreakerModule } from './circuit-breaker/circuit-breaker.module';
import { AssetMenagementCgesCircuitBreakerTypeModule } from './circuit-breaker-type/circuit-breaker-type.module';
import { AssetMenagementCgesCircuitBreakerDriveModule } from './circuit-breaker-drive/circuit-breaker-drive.module';
import { AssetMenagementCgesCurrentMeasuringTransformerModule } from './current-measuring-transformer/current-measuring-transformer.module';
import { AssetMenagementCgesCmtTypeModule } from './cmt-type/cmt-type.module';
import { AssetMenagementCgesVoltageMeasuringTransformerModule } from './voltage-measuring-transformer/voltage-measuring-transformer.module';
import { AssetMenagementCgesVmtTypeModule } from './vmt-type/vmt-type.module';
import { AssetMenagementCgesGroundingSticksModule } from './grounding-sticks/grounding-sticks.module';
import { AssetMenagementCgesGroundSticksTypeModule } from './ground-sticks-type/ground-sticks-type.module';
import { AssetMenagementCgesGroundSticksDriveModule } from './ground-sticks-drive/ground-sticks-drive.module';
import { AssetMenagementCgesSurgeArresterModule } from './surge-arrester/surge-arrester.module';
import { AssetMenagementCgesSurgeArresterTypeModule } from './surge-arrester-type/surge-arrester-type.module';
import { AssetMenagementCgesEmployeeModule } from './employee/employee.module';
import { AssetMenagementCgesPhonePrivilageModule } from './phone-privilage/phone-privilage.module';
import { AssetMenagementCgesEmployeeGroupModule } from './employee-group/employee-group.module';
import { AssetMenagementCgesSectorModule } from './sector/sector.module';
import { AssetMenagementCgesJobModule } from './job/job.module';
import { AssetMenagementCgesJobStatusModule } from './job-status/job-status.module';
import { AssetMenagementCgesQualificationModule } from './qualification/qualification.module';
import { AssetMenagementCgesContractModule } from './contract/contract.module';
import { AssetMenagementCgesDepartmanModule } from './departman/departman.module';
import { AssetMenagementCgesBoardOfDirectorsModule } from './board-of-directors/board-of-directors.module';
import { AssetMenagementCgesBoardMemberModule } from './board-member/board-member.module';
import { AssetMenagementCgesTitleInBoardModule } from './title-in-board/title-in-board.module';
import { AssetMenagementCgesAssetModule } from './asset/asset.module';
import { AssetMenagementCgesTypeModule } from './type/type.module';
import { AssetMenagementCgesLocationModule } from './location/location.module';
import { AssetMenagementCgesAssetStatusModule } from './asset-status/asset-status.module';
import { AssetMenagementCgesAssetConditionModule } from './asset-condition/asset-condition.module';
import { AssetMenagementCgesServiceModule } from './service/service.module';
import { AssetMenagementCgesServiceTypeModule } from './service-type/service-type.module';
import { AssetMenagementCgesServiceCompanyModule } from './service-company/service-company.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        AssetMenagementCgesOhlModule,
        AssetMenagementCgesVoltageLevelModule,
        AssetMenagementCgesConductorCrossSectModule,
        AssetMenagementCgesEarthWireCrossSectModule,
        AssetMenagementCgesTconnectionModule,
        AssetMenagementCgesPylonTypeModule,
        AssetMenagementCgesCorrectionFactorModule,
        AssetMenagementCgesThermalLimitModule,
        AssetMenagementCgesSubstationModule,
        AssetMenagementCgesFacilityModule,
        AssetMenagementCgesFacilityMaintainingCoModule,
        AssetMenagementCgesTransformerModule,
        AssetMenagementCgesTransformerTypeModule,
        AssetMenagementCgesTransformatorNumberModule,
        AssetMenagementCgesManufacturerModule,
        AssetMenagementCgesPowerModule,
        AssetMenagementCgesTransmissionRatioModule,
        AssetMenagementCgesCouplingModule,
        AssetMenagementCgesElementStatusModule,
        AssetMenagementCgesElementConditionModule,
        AssetMenagementCgesFieldModule,
        AssetMenagementCgesDisconnectorBusBarModule,
        AssetMenagementCgesDisconnectorLineExitModule,
        AssetMenagementCgesDisconnectorTypeModule,
        AssetMenagementCgesDisconnectorDriveModule,
        AssetMenagementCgesCircuitBreakerModule,
        AssetMenagementCgesCircuitBreakerTypeModule,
        AssetMenagementCgesCircuitBreakerDriveModule,
        AssetMenagementCgesCurrentMeasuringTransformerModule,
        AssetMenagementCgesCmtTypeModule,
        AssetMenagementCgesVoltageMeasuringTransformerModule,
        AssetMenagementCgesVmtTypeModule,
        AssetMenagementCgesGroundingSticksModule,
        AssetMenagementCgesGroundSticksTypeModule,
        AssetMenagementCgesGroundSticksDriveModule,
        AssetMenagementCgesSurgeArresterModule,
        AssetMenagementCgesSurgeArresterTypeModule,
        AssetMenagementCgesEmployeeModule,
        AssetMenagementCgesPhonePrivilageModule,
        AssetMenagementCgesEmployeeGroupModule,
        AssetMenagementCgesSectorModule,
        AssetMenagementCgesJobModule,
        AssetMenagementCgesJobStatusModule,
        AssetMenagementCgesQualificationModule,
        AssetMenagementCgesContractModule,
        AssetMenagementCgesDepartmanModule,
        AssetMenagementCgesBoardOfDirectorsModule,
        AssetMenagementCgesBoardMemberModule,
        AssetMenagementCgesTitleInBoardModule,
        AssetMenagementCgesAssetModule,
        AssetMenagementCgesTypeModule,
        AssetMenagementCgesLocationModule,
        AssetMenagementCgesAssetStatusModule,
        AssetMenagementCgesAssetConditionModule,
        AssetMenagementCgesServiceModule,
        AssetMenagementCgesServiceTypeModule,
        AssetMenagementCgesServiceCompanyModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssetMenagementCgesEntityModule {}
