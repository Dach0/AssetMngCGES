export interface ICoupling {
    id?: number;
    coupling?: string;
}

export class Coupling implements ICoupling {
    constructor(public id?: number, public coupling?: string) {}
}
