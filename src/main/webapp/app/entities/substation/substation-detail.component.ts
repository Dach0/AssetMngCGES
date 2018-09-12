import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISubstation } from 'app/shared/model/substation.model';

@Component({
    selector: 'jhi-substation-detail',
    templateUrl: './substation-detail.component.html'
})
export class SubstationDetailComponent implements OnInit {
    substation: ISubstation;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ substation }) => {
            this.substation = substation;
        });
    }

    previousState() {
        window.history.back();
    }
}
