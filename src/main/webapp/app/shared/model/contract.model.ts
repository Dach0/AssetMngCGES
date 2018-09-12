export interface IContract {
    id?: number;
    contractType?: string;
}

export class Contract implements IContract {
    constructor(public id?: number, public contractType?: string) {}
}
