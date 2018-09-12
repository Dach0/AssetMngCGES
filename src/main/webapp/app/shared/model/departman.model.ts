export interface IDepartman {
    id?: number;
    depName?: string;
}

export class Departman implements IDepartman {
    constructor(public id?: number, public depName?: string) {}
}
