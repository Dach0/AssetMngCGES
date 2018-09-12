import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDisconnectorDrive } from 'app/shared/model/disconnector-drive.model';

@Component({
    selector: 'jhi-disconnector-drive-detail',
    templateUrl: './disconnector-drive-detail.component.html'
})
export class DisconnectorDriveDetailComponent implements OnInit {
    disconnectorDrive: IDisconnectorDrive;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ disconnectorDrive }) => {
            this.disconnectorDrive = disconnectorDrive;
        });
    }

    previousState() {
        window.history.back();
    }
}
