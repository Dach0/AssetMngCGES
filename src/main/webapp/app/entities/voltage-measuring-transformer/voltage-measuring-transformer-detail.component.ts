import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVoltageMeasuringTransformer } from 'app/shared/model/voltage-measuring-transformer.model';

@Component({
    selector: 'jhi-voltage-measuring-transformer-detail',
    templateUrl: './voltage-measuring-transformer-detail.component.html'
})
export class VoltageMeasuringTransformerDetailComponent implements OnInit {
    voltageMeasuringTransformer: IVoltageMeasuringTransformer;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ voltageMeasuringTransformer }) => {
            this.voltageMeasuringTransformer = voltageMeasuringTransformer;
        });
    }

    previousState() {
        window.history.back();
    }
}
