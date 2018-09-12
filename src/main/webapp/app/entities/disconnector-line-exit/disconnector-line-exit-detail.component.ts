import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDisconnectorLineExit } from 'app/shared/model/disconnector-line-exit.model';

@Component({
    selector: 'jhi-disconnector-line-exit-detail',
    templateUrl: './disconnector-line-exit-detail.component.html'
})
export class DisconnectorLineExitDetailComponent implements OnInit {
    disconnectorLineExit: IDisconnectorLineExit;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ disconnectorLineExit }) => {
            this.disconnectorLineExit = disconnectorLineExit;
        });
    }

    previousState() {
        window.history.back();
    }
}
