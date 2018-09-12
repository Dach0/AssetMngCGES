import { ITitleInBoard } from 'app/shared/model//title-in-board.model';
import { IQualification } from 'app/shared/model//qualification.model';

export interface IBoardMember {
    id?: number;
    name?: string;
    lastName?: string;
    phone?: string;
    email?: string;
    picture?: string;
    boardTitle?: ITitleInBoard;
    qualification?: IQualification;
}

export class BoardMember implements IBoardMember {
    constructor(
        public id?: number,
        public name?: string,
        public lastName?: string,
        public phone?: string,
        public email?: string,
        public picture?: string,
        public boardTitle?: ITitleInBoard,
        public qualification?: IQualification
    ) {}
}
