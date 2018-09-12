import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEarthWireCrossSect } from 'app/shared/model/earth-wire-cross-sect.model';

@Component({
    selector: 'jhi-earth-wire-cross-sect-detail',
    templateUrl: './earth-wire-cross-sect-detail.component.html'
})
export class EarthWireCrossSectDetailComponent implements OnInit {
    earthWireCrossSect: IEarthWireCrossSect;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ earthWireCrossSect }) => {
            this.earthWireCrossSect = earthWireCrossSect;
        });
    }

    previousState() {
        window.history.back();
    }
}
