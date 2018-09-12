import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICurrentMeasuringTransformer } from 'app/shared/model/current-measuring-transformer.model';

@Component({
    selector: 'jhi-current-measuring-transformer-detail',
    templateUrl: './current-measuring-transformer-detail.component.html'
})
export class CurrentMeasuringTransformerDetailComponent implements OnInit {
    currentMeasuringTransformer: ICurrentMeasuringTransformer;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ currentMeasuringTransformer }) => {
            this.currentMeasuringTransformer = currentMeasuringTransformer;
        });
    }

    previousState() {
        window.history.back();
    }
}
