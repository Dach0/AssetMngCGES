export interface IVmtType {
    id?: number;
    vmtType?: string;
}

export class VmtType implements IVmtType {
    constructor(public id?: number, public vmtType?: string) {}
}
