import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IVmtType } from 'app/shared/model/vmt-type.model';
import { Principal } from 'app/core';
import { VmtTypeService } from './vmt-type.service';

@Component({
    selector: 'jhi-vmt-type',
    templateUrl: './vmt-type.component.html'
})
export class VmtTypeComponent implements OnInit, OnDestroy {
    vmtTypes: IVmtType[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private vmtTypeService: VmtTypeService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.vmtTypeService.query().subscribe(
            (res: HttpResponse<IVmtType[]>) => {
                this.vmtTypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInVmtTypes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IVmtType) {
        return item.id;
    }

    registerChangeInVmtTypes() {
        this.eventSubscriber = this.eventManager.subscribe('vmtTypeListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
