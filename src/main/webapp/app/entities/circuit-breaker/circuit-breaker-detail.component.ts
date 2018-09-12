import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICircuitBreaker } from 'app/shared/model/circuit-breaker.model';

@Component({
    selector: 'jhi-circuit-breaker-detail',
    templateUrl: './circuit-breaker-detail.component.html'
})
export class CircuitBreakerDetailComponent implements OnInit {
    circuitBreaker: ICircuitBreaker;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ circuitBreaker }) => {
            this.circuitBreaker = circuitBreaker;
        });
    }

    previousState() {
        window.history.back();
    }
}
