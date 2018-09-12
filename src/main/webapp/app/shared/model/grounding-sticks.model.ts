import { IGroundSticksDrive } from 'app/shared/model//ground-sticks-drive.model';
import { IGroundSticksType } from 'app/shared/model//ground-sticks-type.model';
import { IManufacturer } from 'app/shared/model//manufacturer.model';
import { ISubstation } from 'app/shared/model//substation.model';
import { IField } from 'app/shared/model//field.model';

export interface IGroundingSticks {
    id?: number;
    productionYear?: number;
    current?: number;
    serial?: string;
    picture?: string;
    drive?: IGroundSticksDrive;
    type?: IGroundSticksType;
    manufactured?: IManufacturer;
    substation?: ISubstation;
    field?: IField;
}

export class GroundingSticks implements IGroundingSticks {
    constructor(
        public id?: number,
        public productionYear?: number,
        public current?: number,
        public serial?: string,
        public picture?: string,
        public drive?: IGroundSticksDrive,
        public type?: IGroundSticksType,
        public manufactured?: IManufacturer,
        public substation?: ISubstation,
        public field?: IField
    ) {}
}
