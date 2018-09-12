import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDisconnectorType } from 'app/shared/model/disconnector-type.model';

@Component({
    selector: 'jhi-disconnector-type-detail',
    templateUrl: './disconnector-type-detail.component.html'
})
export class DisconnectorTypeDetailComponent implements OnInit {
    disconnectorType: IDisconnectorType;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ disconnectorType }) => {
            this.disconnectorType = disconnectorType;
        });
    }

    previousState() {
        window.history.back();
    }
}
