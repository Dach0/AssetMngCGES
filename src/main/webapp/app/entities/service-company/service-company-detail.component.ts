import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IServiceCompany } from 'app/shared/model/service-company.model';

@Component({
    selector: 'jhi-service-company-detail',
    templateUrl: './service-company-detail.component.html'
})
export class ServiceCompanyDetailComponent implements OnInit {
    serviceCompany: IServiceCompany;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ serviceCompany }) => {
            this.serviceCompany = serviceCompany;
        });
    }

    previousState() {
        window.history.back();
    }
}
