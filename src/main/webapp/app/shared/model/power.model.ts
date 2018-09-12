export interface IPower {
    id?: number;
    power?: string;
}

export class Power implements IPower {
    constructor(public id?: number, public power?: string) {}
}
