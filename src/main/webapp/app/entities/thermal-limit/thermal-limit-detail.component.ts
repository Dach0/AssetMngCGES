import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IThermalLimit } from 'app/shared/model/thermal-limit.model';

@Component({
    selector: 'jhi-thermal-limit-detail',
    templateUrl: './thermal-limit-detail.component.html'
})
export class ThermalLimitDetailComponent implements OnInit {
    thermalLimit: IThermalLimit;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ thermalLimit }) => {
            this.thermalLimit = thermalLimit;
        });
    }

    previousState() {
        window.history.back();
    }
}
