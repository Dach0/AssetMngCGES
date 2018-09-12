import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ITitleInBoard } from 'app/shared/model/title-in-board.model';
import { TitleInBoardService } from './title-in-board.service';

@Component({
    selector: 'jhi-title-in-board-update',
    templateUrl: './title-in-board-update.component.html'
})
export class TitleInBoardUpdateComponent implements OnInit {
    private _titleInBoard: ITitleInBoard;
    isSaving: boolean;

    constructor(private titleInBoardService: TitleInBoardService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ titleInBoard }) => {
            this.titleInBoard = titleInBoard;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.titleInBoard.id !== undefined) {
            this.subscribeToSaveResponse(this.titleInBoardService.update(this.titleInBoard));
        } else {
            this.subscribeToSaveResponse(this.titleInBoardService.create(this.titleInBoard));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITitleInBoard>>) {
        result.subscribe((res: HttpResponse<ITitleInBoard>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get titleInBoard() {
        return this._titleInBoard;
    }

    set titleInBoard(titleInBoard: ITitleInBoard) {
        this._titleInBoard = titleInBoard;
    }
}
