import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IBoardOfDirectors } from 'app/shared/model/board-of-directors.model';
import { Principal } from 'app/core';
import { BoardOfDirectorsService } from './board-of-directors.service';

@Component({
    selector: 'jhi-board-of-directors',
    templateUrl: './board-of-directors.component.html'
})
export class BoardOfDirectorsComponent implements OnInit, OnDestroy {
    boardOfDirectors: IBoardOfDirectors[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private boardOfDirectorsService: BoardOfDirectorsService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.boardOfDirectorsService.query().subscribe(
            (res: HttpResponse<IBoardOfDirectors[]>) => {
                this.boardOfDirectors = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInBoardOfDirectors();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IBoardOfDirectors) {
        return item.id;
    }

    registerChangeInBoardOfDirectors() {
        this.eventSubscriber = this.eventManager.subscribe('boardOfDirectorsListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
