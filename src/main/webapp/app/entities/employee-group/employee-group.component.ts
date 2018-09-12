import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IEmployeeGroup } from 'app/shared/model/employee-group.model';
import { Principal } from 'app/core';
import { EmployeeGroupService } from './employee-group.service';

@Component({
    selector: 'jhi-employee-group',
    templateUrl: './employee-group.component.html'
})
export class EmployeeGroupComponent implements OnInit, OnDestroy {
    employeeGroups: IEmployeeGroup[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private employeeGroupService: EmployeeGroupService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.employeeGroupService.query().subscribe(
            (res: HttpResponse<IEmployeeGroup[]>) => {
                this.employeeGroups = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInEmployeeGroups();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IEmployeeGroup) {
        return item.id;
    }

    registerChangeInEmployeeGroups() {
        this.eventSubscriber = this.eventManager.subscribe('employeeGroupListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
