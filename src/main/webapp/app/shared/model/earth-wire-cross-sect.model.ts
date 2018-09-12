export interface IEarthWireCrossSect {
    id?: number;
    earthWireCrossSection?: string;
}

export class EarthWireCrossSect implements IEarthWireCrossSect {
    constructor(public id?: number, public earthWireCrossSection?: string) {}
}
