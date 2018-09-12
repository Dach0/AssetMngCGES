import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISurgeArresterType } from 'app/shared/model/surge-arrester-type.model';

@Component({
    selector: 'jhi-surge-arrester-type-detail',
    templateUrl: './surge-arrester-type-detail.component.html'
})
export class SurgeArresterTypeDetailComponent implements OnInit {
    surgeArresterType: ISurgeArresterType;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ surgeArresterType }) => {
            this.surgeArresterType = surgeArresterType;
        });
    }

    previousState() {
        window.history.back();
    }
}
