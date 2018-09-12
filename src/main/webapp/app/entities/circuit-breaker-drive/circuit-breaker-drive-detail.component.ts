import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICircuitBreakerDrive } from 'app/shared/model/circuit-breaker-drive.model';

@Component({
    selector: 'jhi-circuit-breaker-drive-detail',
    templateUrl: './circuit-breaker-drive-detail.component.html'
})
export class CircuitBreakerDriveDetailComponent implements OnInit {
    circuitBreakerDrive: ICircuitBreakerDrive;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ circuitBreakerDrive }) => {
            this.circuitBreakerDrive = circuitBreakerDrive;
        });
    }

    previousState() {
        window.history.back();
    }
}
