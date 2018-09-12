import { IVoltageLevel } from 'app/shared/model//voltage-level.model';
import { IFacilityMaintainingCo } from 'app/shared/model//facility-maintaining-co.model';
import { ISubstation } from 'app/shared/model//substation.model';

export interface IFacility {
    id?: number;
    inOperationSince?: number;
    power?: number;
    numOfTransformers?: number;
    busbars?: string;
    numOfFields?: number;
    voltageLevel?: IVoltageLevel;
    maintaining?: IFacilityMaintainingCo;
    substations?: ISubstation[];
}

export class Facility implements IFacility {
    constructor(
        public id?: number,
        public inOperationSince?: number,
        public power?: number,
        public numOfTransformers?: number,
        public busbars?: string,
        public numOfFields?: number,
        public voltageLevel?: IVoltageLevel,
        public maintaining?: IFacilityMaintainingCo,
        public substations?: ISubstation[]
    ) {}
}
