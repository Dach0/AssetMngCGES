import { ISubstation } from 'app/shared/model//substation.model';
import { ITconnection } from 'app/shared/model//tconnection.model';
import { IVoltageLevel } from 'app/shared/model//voltage-level.model';
import { IPylonType } from 'app/shared/model//pylon-type.model';
import { IThermalLimit } from 'app/shared/model//thermal-limit.model';
import { IConductorCrossSect } from 'app/shared/model//conductor-cross-sect.model';
import { IEarthWireCrossSect } from 'app/shared/model//earth-wire-cross-sect.model';

export interface IOhl {
    id?: number;
    ohlNumber?: string;
    operationYear?: number;
    lengthTotal?: number;
    lengthInMne?: number;
    rOhmPerPhaseInMne?: number;
    rOhmPerPhaseTotal?: number;
    xOhmPerPhaseInMne?: number;
    xOhmPerPhaseTotal?: number;
    bOhmPerPhaseInMne?: number;
    bOhmPerPhaseTotal?: number;
    r0OhmPerPhaseInMne?: number;
    r0OhmPerPhaseTotal?: number;
    x0OhmPerPhaseInMne?: number;
    x0OhmPerPhaseTotal?: number;
    pylonNumber?: number;
    isolatorNumber?: number;
    substation1?: ISubstation;
    substation2?: ISubstation;
    tConnection?: ITconnection;
    voltageLevel?: IVoltageLevel;
    ohlConstructionPart?: IPylonType;
    thermalLimit?: IThermalLimit;
    condCrossSect?: IConductorCrossSect;
    earthCrossSect?: IEarthWireCrossSect;
}

export class Ohl implements IOhl {
    constructor(
        public id?: number,
        public ohlNumber?: string,
        public operationYear?: number,
        public lengthTotal?: number,
        public lengthInMne?: number,
        public rOhmPerPhaseInMne?: number,
        public rOhmPerPhaseTotal?: number,
        public xOhmPerPhaseInMne?: number,
        public xOhmPerPhaseTotal?: number,
        public bOhmPerPhaseInMne?: number,
        public bOhmPerPhaseTotal?: number,
        public r0OhmPerPhaseInMne?: number,
        public r0OhmPerPhaseTotal?: number,
        public x0OhmPerPhaseInMne?: number,
        public x0OhmPerPhaseTotal?: number,
        public pylonNumber?: number,
        public isolatorNumber?: number,
        public substation1?: ISubstation,
        public substation2?: ISubstation,
        public tConnection?: ITconnection,
        public voltageLevel?: IVoltageLevel,
        public ohlConstructionPart?: IPylonType,
        public thermalLimit?: IThermalLimit,
        public condCrossSect?: IConductorCrossSect,
        public earthCrossSect?: IEarthWireCrossSect
    ) {}
}
