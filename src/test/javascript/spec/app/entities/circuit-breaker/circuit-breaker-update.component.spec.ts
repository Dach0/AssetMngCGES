/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { CircuitBreakerUpdateComponent } from 'app/entities/circuit-breaker/circuit-breaker-update.component';
import { CircuitBreakerService } from 'app/entities/circuit-breaker/circuit-breaker.service';
import { CircuitBreaker } from 'app/shared/model/circuit-breaker.model';

describe('Component Tests', () => {
    describe('CircuitBreaker Management Update Component', () => {
        let comp: CircuitBreakerUpdateComponent;
        let fixture: ComponentFixture<CircuitBreakerUpdateComponent>;
        let service: CircuitBreakerService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [CircuitBreakerUpdateComponent]
            })
                .overrideTemplate(CircuitBreakerUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CircuitBreakerUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CircuitBreakerService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new CircuitBreaker(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.circuitBreaker = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new CircuitBreaker();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.circuitBreaker = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
