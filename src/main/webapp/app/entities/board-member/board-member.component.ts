import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IBoardMember } from 'app/shared/model/board-member.model';
import { Principal } from 'app/core';
import { BoardMemberService } from './board-member.service';

@Component({
    selector: 'jhi-board-member',
    templateUrl: './board-member.component.html'
})
export class BoardMemberComponent implements OnInit, OnDestroy {
    boardMembers: IBoardMember[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private boardMemberService: BoardMemberService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.boardMemberService.query().subscribe(
            (res: HttpResponse<IBoardMember[]>) => {
                this.boardMembers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInBoardMembers();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IBoardMember) {
        return item.id;
    }

    registerChangeInBoardMembers() {
        this.eventSubscriber = this.eventManager.subscribe('boardMemberListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
