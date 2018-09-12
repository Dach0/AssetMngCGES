import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IPhonePrivilage } from 'app/shared/model/phone-privilage.model';
import { Principal } from 'app/core';
import { PhonePrivilageService } from './phone-privilage.service';

@Component({
    selector: 'jhi-phone-privilage',
    templateUrl: './phone-privilage.component.html'
})
export class PhonePrivilageComponent implements OnInit, OnDestroy {
    phonePrivilages: IPhonePrivilage[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private phonePrivilageService: PhonePrivilageService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.phonePrivilageService.query().subscribe(
            (res: HttpResponse<IPhonePrivilage[]>) => {
                this.phonePrivilages = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInPhonePrivilages();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IPhonePrivilage) {
        return item.id;
    }

    registerChangeInPhonePrivilages() {
        this.eventSubscriber = this.eventManager.subscribe('phonePrivilageListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
