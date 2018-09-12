import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITitleInBoard } from 'app/shared/model/title-in-board.model';

@Component({
    selector: 'jhi-title-in-board-detail',
    templateUrl: './title-in-board-detail.component.html'
})
export class TitleInBoardDetailComponent implements OnInit {
    titleInBoard: ITitleInBoard;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ titleInBoard }) => {
            this.titleInBoard = titleInBoard;
        });
    }

    previousState() {
        window.history.back();
    }
}
