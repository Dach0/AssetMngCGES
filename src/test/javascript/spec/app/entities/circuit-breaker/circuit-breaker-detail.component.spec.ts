/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { CircuitBreakerDetailComponent } from 'app/entities/circuit-breaker/circuit-breaker-detail.component';
import { CircuitBreaker } from 'app/shared/model/circuit-breaker.model';

describe('Component Tests', () => {
    describe('CircuitBreaker Management Detail Component', () => {
        let comp: CircuitBreakerDetailComponent;
        let fixture: ComponentFixture<CircuitBreakerDetailComponent>;
        const route = ({ data: of({ circuitBreaker: new CircuitBreaker(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [CircuitBreakerDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CircuitBreakerDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CircuitBreakerDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.circuitBreaker).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
