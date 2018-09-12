import { IDisconnectorDrive } from 'app/shared/model//disconnector-drive.model';
import { IDisconnectorType } from 'app/shared/model//disconnector-type.model';
import { IManufacturer } from 'app/shared/model//manufacturer.model';
import { ISubstation } from 'app/shared/model//substation.model';
import { IField } from 'app/shared/model//field.model';

export interface IDisconnectorBusBar {
    id?: number;
    productionYear?: number;
    current?: number;
    serial?: string;
    picture?: string;
    drive?: IDisconnectorDrive;
    type?: IDisconnectorType;
    manufactured?: IManufacturer;
    substation?: ISubstation;
    field?: IField;
}

export class DisconnectorBusBar implements IDisconnectorBusBar {
    constructor(
        public id?: number,
        public productionYear?: number,
        public current?: number,
        public serial?: string,
        public picture?: string,
        public drive?: IDisconnectorDrive,
        public type?: IDisconnectorType,
        public manufactured?: IManufacturer,
        public substation?: ISubstation,
        public field?: IField
    ) {}
}
