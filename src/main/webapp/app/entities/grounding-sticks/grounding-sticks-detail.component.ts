import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGroundingSticks } from 'app/shared/model/grounding-sticks.model';

@Component({
    selector: 'jhi-grounding-sticks-detail',
    templateUrl: './grounding-sticks-detail.component.html'
})
export class GroundingSticksDetailComponent implements OnInit {
    groundingSticks: IGroundingSticks;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ groundingSticks }) => {
            this.groundingSticks = groundingSticks;
        });
    }

    previousState() {
        window.history.back();
    }
}
