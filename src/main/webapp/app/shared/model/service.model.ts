import { Moment } from 'moment';
import { IServiceCompany } from 'app/shared/model//service-company.model';
import { IEmployee } from 'app/shared/model//employee.model';
import { IServiceType } from 'app/shared/model//service-type.model';
import { ITransformer } from 'app/shared/model//transformer.model';

export interface IService {
    id?: number;
    serviceDescription?: string;
    repairPrice?: number;
    sparePartsPrice?: number;
    servicedFrom?: Moment;
    servicedTo?: Moment;
    attachments?: string;
    notes?: any;
    doneByComp?: IServiceCompany;
    doneByEmpl?: IEmployee;
    type?: IServiceType;
    transformers?: ITransformer[];
}

export class Service implements IService {
    constructor(
        public id?: number,
        public serviceDescription?: string,
        public repairPrice?: number,
        public sparePartsPrice?: number,
        public servicedFrom?: Moment,
        public servicedTo?: Moment,
        public attachments?: string,
        public notes?: any,
        public doneByComp?: IServiceCompany,
        public doneByEmpl?: IEmployee,
        public type?: IServiceType,
        public transformers?: ITransformer[]
    ) {}
}
