export interface IElementCondition {
    id?: number;
    elementCondition?: string;
}

export class ElementCondition implements IElementCondition {
    constructor(public id?: number, public elementCondition?: string) {}
}
