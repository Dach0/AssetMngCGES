import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBoardOfDirectors } from 'app/shared/model/board-of-directors.model';

@Component({
    selector: 'jhi-board-of-directors-detail',
    templateUrl: './board-of-directors-detail.component.html'
})
export class BoardOfDirectorsDetailComponent implements OnInit {
    boardOfDirectors: IBoardOfDirectors;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ boardOfDirectors }) => {
            this.boardOfDirectors = boardOfDirectors;
        });
    }

    previousState() {
        window.history.back();
    }
}
