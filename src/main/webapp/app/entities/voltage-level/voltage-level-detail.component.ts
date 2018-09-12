import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVoltageLevel } from 'app/shared/model/voltage-level.model';

@Component({
    selector: 'jhi-voltage-level-detail',
    templateUrl: './voltage-level-detail.component.html'
})
export class VoltageLevelDetailComponent implements OnInit {
    voltageLevel: IVoltageLevel;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ voltageLevel }) => {
            this.voltageLevel = voltageLevel;
        });
    }

    previousState() {
        window.history.back();
    }
}
