import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICircuitBreakerType } from 'app/shared/model/circuit-breaker-type.model';

@Component({
    selector: 'jhi-circuit-breaker-type-detail',
    templateUrl: './circuit-breaker-type-detail.component.html'
})
export class CircuitBreakerTypeDetailComponent implements OnInit {
    circuitBreakerType: ICircuitBreakerType;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ circuitBreakerType }) => {
            this.circuitBreakerType = circuitBreakerType;
        });
    }

    previousState() {
        window.history.back();
    }
}
