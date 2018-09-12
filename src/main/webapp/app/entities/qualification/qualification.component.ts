import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IQualification } from 'app/shared/model/qualification.model';
import { Principal } from 'app/core';
import { QualificationService } from './qualification.service';

@Component({
    selector: 'jhi-qualification',
    templateUrl: './qualification.component.html'
})
export class QualificationComponent implements OnInit, OnDestroy {
    qualifications: IQualification[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private qualificationService: QualificationService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.qualificationService.query().subscribe(
            (res: HttpResponse<IQualification[]>) => {
                this.qualifications = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInQualifications();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IQualification) {
        return item.id;
    }

    registerChangeInQualifications() {
        this.eventSubscriber = this.eventManager.subscribe('qualificationListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
