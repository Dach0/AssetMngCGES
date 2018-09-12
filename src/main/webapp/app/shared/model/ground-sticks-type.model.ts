export interface IGroundSticksType {
    id?: number;
    gsType?: string;
}

export class GroundSticksType implements IGroundSticksType {
    constructor(public id?: number, public gsType?: string) {}
}
