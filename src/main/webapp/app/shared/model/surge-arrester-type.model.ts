export interface ISurgeArresterType {
    id?: number;
    saType?: string;
}

export class SurgeArresterType implements ISurgeArresterType {
    constructor(public id?: number, public saType?: string) {}
}
