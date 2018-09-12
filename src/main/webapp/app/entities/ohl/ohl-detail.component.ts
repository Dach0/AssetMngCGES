import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOhl } from 'app/shared/model/ohl.model';

@Component({
    selector: 'jhi-ohl-detail',
    templateUrl: './ohl-detail.component.html'
})
export class OhlDetailComponent implements OnInit {
    ohl: IOhl;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ ohl }) => {
            this.ohl = ohl;
        });
    }

    previousState() {
        window.history.back();
    }
}
