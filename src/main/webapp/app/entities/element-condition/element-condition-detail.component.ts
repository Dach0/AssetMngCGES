import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IElementCondition } from 'app/shared/model/element-condition.model';

@Component({
    selector: 'jhi-element-condition-detail',
    templateUrl: './element-condition-detail.component.html'
})
export class ElementConditionDetailComponent implements OnInit {
    elementCondition: IElementCondition;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ elementCondition }) => {
            this.elementCondition = elementCondition;
        });
    }

    previousState() {
        window.history.back();
    }
}
