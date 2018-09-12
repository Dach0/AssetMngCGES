export interface ITransformerType {
    id?: number;
    type?: string;
}

export class TransformerType implements ITransformerType {
    constructor(public id?: number, public type?: string) {}
}
