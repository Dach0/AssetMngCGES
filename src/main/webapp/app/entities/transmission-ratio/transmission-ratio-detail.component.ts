import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITransmissionRatio } from 'app/shared/model/transmission-ratio.model';

@Component({
    selector: 'jhi-transmission-ratio-detail',
    templateUrl: './transmission-ratio-detail.component.html'
})
export class TransmissionRatioDetailComponent implements OnInit {
    transmissionRatio: ITransmissionRatio;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ transmissionRatio }) => {
            this.transmissionRatio = transmissionRatio;
        });
    }

    previousState() {
        window.history.back();
    }
}
