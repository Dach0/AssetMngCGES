import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IConductorCrossSect } from 'app/shared/model/conductor-cross-sect.model';

@Component({
    selector: 'jhi-conductor-cross-sect-detail',
    templateUrl: './conductor-cross-sect-detail.component.html'
})
export class ConductorCrossSectDetailComponent implements OnInit {
    conductorCrossSect: IConductorCrossSect;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ conductorCrossSect }) => {
            this.conductorCrossSect = conductorCrossSect;
        });
    }

    previousState() {
        window.history.back();
    }
}
