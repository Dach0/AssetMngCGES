import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITransformatorNumber } from 'app/shared/model/transformator-number.model';

@Component({
    selector: 'jhi-transformator-number-detail',
    templateUrl: './transformator-number-detail.component.html'
})
export class TransformatorNumberDetailComponent implements OnInit {
    transformatorNumber: ITransformatorNumber;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ transformatorNumber }) => {
            this.transformatorNumber = transformatorNumber;
        });
    }

    previousState() {
        window.history.back();
    }
}
