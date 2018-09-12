export interface IType {
    id?: number;
    typeName?: string;
}

export class Type implements IType {
    constructor(public id?: number, public typeName?: string) {}
}
