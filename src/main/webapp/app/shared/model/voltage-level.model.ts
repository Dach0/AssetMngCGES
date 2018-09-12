import { ICorrectionFactor } from 'app/shared/model//correction-factor.model';

export interface IVoltageLevel {
    id?: number;
    voltageLevel?: number;
    correctionFactor?: ICorrectionFactor;
}

export class VoltageLevel implements IVoltageLevel {
    constructor(public id?: number, public voltageLevel?: number, public correctionFactor?: ICorrectionFactor) {}
}
