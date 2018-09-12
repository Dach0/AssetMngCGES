export interface IElementStatus {
    id?: number;
    elementStatus?: string;
}

export class ElementStatus implements IElementStatus {
    constructor(public id?: number, public elementStatus?: string) {}
}
