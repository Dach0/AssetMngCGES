import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGroundSticksDrive } from 'app/shared/model/ground-sticks-drive.model';

@Component({
    selector: 'jhi-ground-sticks-drive-detail',
    templateUrl: './ground-sticks-drive-detail.component.html'
})
export class GroundSticksDriveDetailComponent implements OnInit {
    groundSticksDrive: IGroundSticksDrive;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ groundSticksDrive }) => {
            this.groundSticksDrive = groundSticksDrive;
        });
    }

    previousState() {
        window.history.back();
    }
}
