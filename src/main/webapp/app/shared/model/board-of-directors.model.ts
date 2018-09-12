import { Moment } from 'moment';
import { IBoardMember } from 'app/shared/model//board-member.model';

export interface IBoardOfDirectors {
    id?: number;
    startDate?: Moment;
    endDate?: Moment;
    executives?: IBoardMember[];
    execAssistents?: IBoardMember[];
    member1S?: IBoardMember[];
    member2S?: IBoardMember[];
    member3S?: IBoardMember[];
    member4S?: IBoardMember[];
    member5S?: IBoardMember[];
}

export class BoardOfDirectors implements IBoardOfDirectors {
    constructor(
        public id?: number,
        public startDate?: Moment,
        public endDate?: Moment,
        public executives?: IBoardMember[],
        public execAssistents?: IBoardMember[],
        public member1S?: IBoardMember[],
        public member2S?: IBoardMember[],
        public member3S?: IBoardMember[],
        public member4S?: IBoardMember[],
        public member5S?: IBoardMember[]
    ) {}
}
