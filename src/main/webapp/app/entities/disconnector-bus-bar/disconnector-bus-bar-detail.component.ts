import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDisconnectorBusBar } from 'app/shared/model/disconnector-bus-bar.model';

@Component({
    selector: 'jhi-disconnector-bus-bar-detail',
    templateUrl: './disconnector-bus-bar-detail.component.html'
})
export class DisconnectorBusBarDetailComponent implements OnInit {
    disconnectorBusBar: IDisconnectorBusBar;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ disconnectorBusBar }) => {
            this.disconnectorBusBar = disconnectorBusBar;
        });
    }

    previousState() {
        window.history.back();
    }
}
