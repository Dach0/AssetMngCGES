import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IServiceType } from 'app/shared/model/service-type.model';

@Component({
    selector: 'jhi-service-type-detail',
    templateUrl: './service-type-detail.component.html'
})
export class ServiceTypeDetailComponent implements OnInit {
    serviceType: IServiceType;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ serviceType }) => {
            this.serviceType = serviceType;
        });
    }

    previousState() {
        window.history.back();
    }
}
