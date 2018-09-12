import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICorrectionFactor } from 'app/shared/model/correction-factor.model';

@Component({
    selector: 'jhi-correction-factor-detail',
    templateUrl: './correction-factor-detail.component.html'
})
export class CorrectionFactorDetailComponent implements OnInit {
    correctionFactor: ICorrectionFactor;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ correctionFactor }) => {
            this.correctionFactor = correctionFactor;
        });
    }

    previousState() {
        window.history.back();
    }
}
