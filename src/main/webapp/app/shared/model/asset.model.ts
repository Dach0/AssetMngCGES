import { Moment } from 'moment';
import { IType } from 'app/shared/model//type.model';
import { ILocation } from 'app/shared/model//location.model';
import { IAssetStatus } from 'app/shared/model//asset-status.model';
import { IAssetCondition } from 'app/shared/model//asset-condition.model';
import { IEmployee } from 'app/shared/model//employee.model';

export interface IAsset {
    id?: number;
    description?: string;
    manufacturer?: string;
    brand?: string;
    model?: string;
    picture?: string;
    serialNumber?: string;
    dateOfObligation?: Moment;
    purchasePrice?: number;
    marketPrice?: number;
    scrapPrice?: number;
    purchasedDate?: Moment;
    inServiceDate?: Moment;
    warrenty?: Moment;
    notes?: any;
    attachments?: string;
    type?: IType;
    location?: ILocation;
    status?: IAssetStatus;
    condition?: IAssetCondition;
    asset?: IEmployee;
}

export class Asset implements IAsset {
    constructor(
        public id?: number,
        public description?: string,
        public manufacturer?: string,
        public brand?: string,
        public model?: string,
        public picture?: string,
        public serialNumber?: string,
        public dateOfObligation?: Moment,
        public purchasePrice?: number,
        public marketPrice?: number,
        public scrapPrice?: number,
        public purchasedDate?: Moment,
        public inServiceDate?: Moment,
        public warrenty?: Moment,
        public notes?: any,
        public attachments?: string,
        public type?: IType,
        public location?: ILocation,
        public status?: IAssetStatus,
        public condition?: IAssetCondition,
        public asset?: IEmployee
    ) {}
}
