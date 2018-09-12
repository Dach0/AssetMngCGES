import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITransformerType } from 'app/shared/model/transformer-type.model';

@Component({
    selector: 'jhi-transformer-type-detail',
    templateUrl: './transformer-type-detail.component.html'
})
export class TransformerTypeDetailComponent implements OnInit {
    transformerType: ITransformerType;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ transformerType }) => {
            this.transformerType = transformerType;
        });
    }

    previousState() {
        window.history.back();
    }
}
