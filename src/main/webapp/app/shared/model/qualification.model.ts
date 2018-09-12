export interface IQualification {
    id?: number;
    proffQualification?: string;
}

export class Qualification implements IQualification {
    constructor(public id?: number, public proffQualification?: string) {}
}
