import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPower } from 'app/shared/model/power.model';

@Component({
    selector: 'jhi-power-detail',
    templateUrl: './power-detail.component.html'
})
export class PowerDetailComponent implements OnInit {
    power: IPower;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ power }) => {
            this.power = power;
        });
    }

    previousState() {
        window.history.back();
    }
}
