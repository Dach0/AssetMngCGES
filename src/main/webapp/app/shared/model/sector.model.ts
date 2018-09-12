export interface ISector {
    id?: number;
    sectorName?: string;
}

export class Sector implements ISector {
    constructor(public id?: number, public sectorName?: string) {}
}
