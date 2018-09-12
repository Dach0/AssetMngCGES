export interface IJobStatus {
    id?: number;
    jobStatus?: string;
}

export class JobStatus implements IJobStatus {
    constructor(public id?: number, public jobStatus?: string) {}
}
