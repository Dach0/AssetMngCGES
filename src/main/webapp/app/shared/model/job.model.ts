export interface IJob {
    id?: number;
    jobDescription?: string;
}

export class Job implements IJob {
    constructor(public id?: number, public jobDescription?: string) {}
}
