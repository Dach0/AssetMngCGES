import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IEmployeeGroup } from 'app/shared/model/employee-group.model';
import { EmployeeGroupService } from './employee-group.service';

@Component({
    selector: 'jhi-employee-group-update',
    templateUrl: './employee-group-update.component.html'
})
export class EmployeeGroupUpdateComponent implements OnInit {
    private _employeeGroup: IEmployeeGroup;
    isSaving: boolean;

    constructor(private employeeGroupService: EmployeeGroupService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ employeeGroup }) => {
            this.employeeGroup = employeeGroup;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.employeeGroup.id !== undefined) {
            this.subscribeToSaveResponse(this.employeeGroupService.update(this.employeeGroup));
        } else {
            this.subscribeToSaveResponse(this.employeeGroupService.create(this.employeeGroup));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEmployeeGroup>>) {
        result.subscribe((res: HttpResponse<IEmployeeGroup>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get employeeGroup() {
        return this._employeeGroup;
    }

    set employeeGroup(employeeGroup: IEmployeeGroup) {
        this._employeeGroup = employeeGroup;
    }
}
