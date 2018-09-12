import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IBoardMember } from 'app/shared/model/board-member.model';
import { BoardMemberService } from './board-member.service';
import { ITitleInBoard } from 'app/shared/model/title-in-board.model';
import { TitleInBoardService } from 'app/entities/title-in-board';
import { IQualification } from 'app/shared/model/qualification.model';
import { QualificationService } from 'app/entities/qualification';

@Component({
    selector: 'jhi-board-member-update',
    templateUrl: './board-member-update.component.html'
})
export class BoardMemberUpdateComponent implements OnInit {
    private _boardMember: IBoardMember;
    isSaving: boolean;

    titleinboards: ITitleInBoard[];

    qualifications: IQualification[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private boardMemberService: BoardMemberService,
        private titleInBoardService: TitleInBoardService,
        private qualificationService: QualificationService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ boardMember }) => {
            this.boardMember = boardMember;
        });
        this.titleInBoardService.query().subscribe(
            (res: HttpResponse<ITitleInBoard[]>) => {
                this.titleinboards = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.qualificationService.query().subscribe(
            (res: HttpResponse<IQualification[]>) => {
                this.qualifications = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.boardMember.id !== undefined) {
            this.subscribeToSaveResponse(this.boardMemberService.update(this.boardMember));
        } else {
            this.subscribeToSaveResponse(this.boardMemberService.create(this.boardMember));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IBoardMember>>) {
        result.subscribe((res: HttpResponse<IBoardMember>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackTitleInBoardById(index: number, item: ITitleInBoard) {
        return item.id;
    }

    trackQualificationById(index: number, item: IQualification) {
        return item.id;
    }
    get boardMember() {
        return this._boardMember;
    }

    set boardMember(boardMember: IBoardMember) {
        this._boardMember = boardMember;
    }
}
