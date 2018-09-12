import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISurgeArrester } from 'app/shared/model/surge-arrester.model';

@Component({
    selector: 'jhi-surge-arrester-detail',
    templateUrl: './surge-arrester-detail.component.html'
})
export class SurgeArresterDetailComponent implements OnInit {
    surgeArrester: ISurgeArrester;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ surgeArrester }) => {
            this.surgeArrester = surgeArrester;
        });
    }

    previousState() {
        window.history.back();
    }
}
