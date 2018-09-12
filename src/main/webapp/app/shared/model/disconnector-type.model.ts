export interface IDisconnectorType {
    id?: number;
    discType?: string;
}

export class DisconnectorType implements IDisconnectorType {
    constructor(public id?: number, public discType?: string) {}
}
