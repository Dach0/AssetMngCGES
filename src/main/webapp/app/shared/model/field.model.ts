export interface IField {
    id?: number;
    name?: string;
}

export class Field implements IField {
    constructor(public id?: number, public name?: string) {}
}
