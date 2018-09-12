import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBoardMember } from 'app/shared/model/board-member.model';

@Component({
    selector: 'jhi-board-member-detail',
    templateUrl: './board-member-detail.component.html'
})
export class BoardMemberDetailComponent implements OnInit {
    boardMember: IBoardMember;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ boardMember }) => {
            this.boardMember = boardMember;
        });
    }

    previousState() {
        window.history.back();
    }
}
