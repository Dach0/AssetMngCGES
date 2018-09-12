export interface ICmtType {
    id?: number;
    cmtType?: string;
}

export class CmtType implements ICmtType {
    constructor(public id?: number, public cmtType?: string) {}
}
