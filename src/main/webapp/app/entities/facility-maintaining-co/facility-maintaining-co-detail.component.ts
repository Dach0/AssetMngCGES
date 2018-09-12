import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFacilityMaintainingCo } from 'app/shared/model/facility-maintaining-co.model';

@Component({
    selector: 'jhi-facility-maintaining-co-detail',
    templateUrl: './facility-maintaining-co-detail.component.html'
})
export class FacilityMaintainingCoDetailComponent implements OnInit {
    facilityMaintainingCo: IFacilityMaintainingCo;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ facilityMaintainingCo }) => {
            this.facilityMaintainingCo = facilityMaintainingCo;
        });
    }

    previousState() {
        window.history.back();
    }
}
