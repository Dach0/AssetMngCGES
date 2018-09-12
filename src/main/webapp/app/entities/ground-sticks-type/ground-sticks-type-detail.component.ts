import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGroundSticksType } from 'app/shared/model/ground-sticks-type.model';

@Component({
    selector: 'jhi-ground-sticks-type-detail',
    templateUrl: './ground-sticks-type-detail.component.html'
})
export class GroundSticksTypeDetailComponent implements OnInit {
    groundSticksType: IGroundSticksType;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ groundSticksType }) => {
            this.groundSticksType = groundSticksType;
        });
    }

    previousState() {
        window.history.back();
    }
}
