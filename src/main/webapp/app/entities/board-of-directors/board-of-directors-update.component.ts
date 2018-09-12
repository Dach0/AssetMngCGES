import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IBoardOfDirectors } from 'app/shared/model/board-of-directors.model';
import { BoardOfDirectorsService } from './board-of-directors.service';
import { IBoardMember } from 'app/shared/model/board-member.model';
import { BoardMemberService } from 'app/entities/board-member';

@Component({
    selector: 'jhi-board-of-directors-update',
    templateUrl: './board-of-directors-update.component.html'
})
export class BoardOfDirectorsUpdateComponent implements OnInit {
    private _boardOfDirectors: IBoardOfDirectors;
    isSaving: boolean;

    boardmembers: IBoardMember[];
    startDateDp: any;
    endDateDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private boardOfDirectorsService: BoardOfDirectorsService,
        private boardMemberService: BoardMemberService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ boardOfDirectors }) => {
            this.boardOfDirectors = boardOfDirectors;
        });
        this.boardMemberService.query().subscribe(
            (res: HttpResponse<IBoardMember[]>) => {
                this.boardmembers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.boardOfDirectors.id !== undefined) {
            this.subscribeToSaveResponse(this.boardOfDirectorsService.update(this.boardOfDirectors));
        } else {
            this.subscribeToSaveResponse(this.boardOfDirectorsService.create(this.boardOfDirectors));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IBoardOfDirectors>>) {
        result.subscribe((res: HttpResponse<IBoardOfDirectors>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackBoardMemberById(index: number, item: IBoardMember) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
    get boardOfDirectors() {
        return this._boardOfDirectors;
    }

    set boardOfDirectors(boardOfDirectors: IBoardOfDirectors) {
        this._boardOfDirectors = boardOfDirectors;
    }
}
