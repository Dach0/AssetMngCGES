import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IContract } from 'app/shared/model/contract.model';
import { Principal } from 'app/core';
import { ContractService } from './contract.service';

@Component({
    selector: 'jhi-contract',
    templateUrl: './contract.component.html'
})
export class ContractComponent implements OnInit, OnDestroy {
    contracts: IContract[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private contractService: ContractService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.contractService.query().subscribe(
            (res: HttpResponse<IContract[]>) => {
                this.contracts = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInContracts();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IContract) {
        return item.id;
    }

    registerChangeInContracts() {
        this.eventSubscriber = this.eventManager.subscribe('contractListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
