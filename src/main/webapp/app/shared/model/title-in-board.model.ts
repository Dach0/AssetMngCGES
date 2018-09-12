export interface ITitleInBoard {
    id?: number;
    title?: string;
}

export class TitleInBoard implements ITitleInBoard {
    constructor(public id?: number, public title?: string) {}
}
