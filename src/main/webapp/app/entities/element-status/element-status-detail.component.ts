import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IElementStatus } from 'app/shared/model/element-status.model';

@Component({
    selector: 'jhi-element-status-detail',
    templateUrl: './element-status-detail.component.html'
})
export class ElementStatusDetailComponent implements OnInit {
    elementStatus: IElementStatus;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ elementStatus }) => {
            this.elementStatus = elementStatus;
        });
    }

    previousState() {
        window.history.back();
    }
}
