/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { CircuitBreakerComponent } from 'app/entities/circuit-breaker/circuit-breaker.component';
import { CircuitBreakerService } from 'app/entities/circuit-breaker/circuit-breaker.service';
import { CircuitBreaker } from 'app/shared/model/circuit-breaker.model';

describe('Component Tests', () => {
    describe('CircuitBreaker Management Component', () => {
        let comp: CircuitBreakerComponent;
        let fixture: ComponentFixture<CircuitBreakerComponent>;
        let service: CircuitBreakerService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [CircuitBreakerComponent],
                providers: []
            })
                .overrideTemplate(CircuitBreakerComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CircuitBreakerComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CircuitBreakerService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new CircuitBreaker(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.circuitBreakers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
