import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICoupling } from 'app/shared/model/coupling.model';

@Component({
    selector: 'jhi-coupling-detail',
    templateUrl: './coupling-detail.component.html'
})
export class CouplingDetailComponent implements OnInit {
    coupling: ICoupling;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ coupling }) => {
            this.coupling = coupling;
        });
    }

    previousState() {
        window.history.back();
    }
}
