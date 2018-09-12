export interface IConductorCrossSect {
    id?: number;
    conductorCrossSection?: string;
}

export class ConductorCrossSect implements IConductorCrossSect {
    constructor(public id?: number, public conductorCrossSection?: string) {}
}
