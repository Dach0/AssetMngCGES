export interface IPhonePrivilage {
    id?: number;
    privilage?: string;
}

export class PhonePrivilage implements IPhonePrivilage {
    constructor(public id?: number, public privilage?: string) {}
}
