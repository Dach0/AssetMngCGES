import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPylonType } from 'app/shared/model/pylon-type.model';

@Component({
    selector: 'jhi-pylon-type-detail',
    templateUrl: './pylon-type-detail.component.html'
})
export class PylonTypeDetailComponent implements OnInit {
    pylonType: IPylonType;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ pylonType }) => {
            this.pylonType = pylonType;
        });
    }

    previousState() {
        window.history.back();
    }
}
