export interface IEmployeeGroup {
    id?: number;
    groupName?: string;
}

export class EmployeeGroup implements IEmployeeGroup {
    constructor(public id?: number, public groupName?: string) {}
}
