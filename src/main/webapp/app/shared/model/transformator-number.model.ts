export interface ITransformatorNumber {
    id?: number;
    tNumber?: string;
}

export class TransformatorNumber implements ITransformatorNumber {
    constructor(public id?: number, public tNumber?: string) {}
}
