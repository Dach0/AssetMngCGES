export interface IAssetStatus {
    id?: number;
    status?: string;
}

export class AssetStatus implements IAssetStatus {
    constructor(public id?: number, public status?: string) {}
}
