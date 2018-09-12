/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { CircuitBreakerTypeDetailComponent } from 'app/entities/circuit-breaker-type/circuit-breaker-type-detail.component';
import { CircuitBreakerType } from 'app/shared/model/circuit-breaker-type.model';

describe('Component Tests', () => {
    describe('CircuitBreakerType Management Detail Component', () => {
        let comp: CircuitBreakerTypeDetailComponent;
        let fixture: ComponentFixture<CircuitBreakerTypeDetailComponent>;
        const route = ({ data: of({ circuitBreakerType: new CircuitBreakerType(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [CircuitBreakerTypeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CircuitBreakerTypeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CircuitBreakerTypeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.circuitBreakerType).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
