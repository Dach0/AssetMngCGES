import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPhonePrivilage } from 'app/shared/model/phone-privilage.model';

@Component({
    selector: 'jhi-phone-privilage-detail',
    templateUrl: './phone-privilage-detail.component.html'
})
export class PhonePrivilageDetailComponent implements OnInit {
    phonePrivilage: IPhonePrivilage;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ phonePrivilage }) => {
            this.phonePrivilage = phonePrivilage;
        });
    }

    previousState() {
        window.history.back();
    }
}
