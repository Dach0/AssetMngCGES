export interface IAssetCondition {
    id?: number;
    conditionDesc?: string;
}

export class AssetCondition implements IAssetCondition {
    constructor(public id?: number, public conditionDesc?: string) {}
}
