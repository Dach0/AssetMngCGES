import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ICorrectionFactor } from 'app/shared/model/correction-factor.model';
import { Principal } from 'app/core';
import { CorrectionFactorService } from './correction-factor.service';

@Component({
    selector: 'jhi-correction-factor',
    templateUrl: './correction-factor.component.html'
})
export class CorrectionFactorComponent implements OnInit, OnDestroy {
    correctionFactors: ICorrectionFactor[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private correctionFactorService: CorrectionFactorService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.correctionFactorService.query().subscribe(
            (res: HttpResponse<ICorrectionFactor[]>) => {
                this.correctionFactors = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInCorrectionFactors();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICorrectionFactor) {
        return item.id;
    }

    registerChangeInCorrectionFactors() {
        this.eventSubscriber = this.eventManager.subscribe('correctionFactorListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
