import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ICmtType } from 'app/shared/model/cmt-type.model';
import { Principal } from 'app/core';
import { CmtTypeService } from './cmt-type.service';

@Component({
    selector: 'jhi-cmt-type',
    templateUrl: './cmt-type.component.html'
})
export class CmtTypeComponent implements OnInit, OnDestroy {
    cmtTypes: ICmtType[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private cmtTypeService: CmtTypeService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.cmtTypeService.query().subscribe(
            (res: HttpResponse<ICmtType[]>) => {
                this.cmtTypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInCmtTypes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICmtType) {
        return item.id;
    }

    registerChangeInCmtTypes() {
        this.eventSubscriber = this.eventManager.subscribe('cmtTypeListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
