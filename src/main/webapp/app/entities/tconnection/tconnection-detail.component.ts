import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITconnection } from 'app/shared/model/tconnection.model';

@Component({
    selector: 'jhi-tconnection-detail',
    templateUrl: './tconnection-detail.component.html'
})
export class TconnectionDetailComponent implements OnInit {
    tconnection: ITconnection;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tconnection }) => {
            this.tconnection = tconnection;
        });
    }

    previousState() {
        window.history.back();
    }
}
