import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEmployeeGroup } from 'app/shared/model/employee-group.model';

@Component({
    selector: 'jhi-employee-group-detail',
    templateUrl: './employee-group-detail.component.html'
})
export class EmployeeGroupDetailComponent implements OnInit {
    employeeGroup: IEmployeeGroup;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ employeeGroup }) => {
            this.employeeGroup = employeeGroup;
        });
    }

    previousState() {
        window.history.back();
    }
}
