import { Moment } from 'moment';
import { IPhonePrivilage } from 'app/shared/model//phone-privilage.model';
import { IEmployeeGroup } from 'app/shared/model//employee-group.model';
import { ISector } from 'app/shared/model//sector.model';
import { IDepartman } from 'app/shared/model//departman.model';
import { IJob } from 'app/shared/model//job.model';
import { IJobStatus } from 'app/shared/model//job-status.model';
import { IQualification } from 'app/shared/model//qualification.model';
import { IContract } from 'app/shared/model//contract.model';

export interface IEmployee {
    id?: number;
    name?: string;
    lastname?: string;
    telNum1?: string;
    telNum2?: string;
    email?: string;
    startDate?: Moment;
    endDate?: Moment;
    pictureEmpl?: string;
    notes?: any;
    attachments?: string;
    phPrivilage?: IPhonePrivilage;
    group?: IEmployeeGroup;
    sector?: ISector;
    departman?: IDepartman;
    jobDesc?: IJob;
    status?: IJobStatus;
    profQualification?: IQualification;
    contractType?: IContract;
}

export class Employee implements IEmployee {
    constructor(
        public id?: number,
        public name?: string,
        public lastname?: string,
        public telNum1?: string,
        public telNum2?: string,
        public email?: string,
        public startDate?: Moment,
        public endDate?: Moment,
        public pictureEmpl?: string,
        public notes?: any,
        public attachments?: string,
        public phPrivilage?: IPhonePrivilage,
        public group?: IEmployeeGroup,
        public sector?: ISector,
        public departman?: IDepartman,
        public jobDesc?: IJob,
        public status?: IJobStatus,
        public profQualification?: IQualification,
        public contractType?: IContract
    ) {}
}
