/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { CircuitBreakerTypeUpdateComponent } from 'app/entities/circuit-breaker-type/circuit-breaker-type-update.component';
import { CircuitBreakerTypeService } from 'app/entities/circuit-breaker-type/circuit-breaker-type.service';
import { CircuitBreakerType } from 'app/shared/model/circuit-breaker-type.model';

describe('Component Tests', () => {
    describe('CircuitBreakerType Management Update Component', () => {
        let comp: CircuitBreakerTypeUpdateComponent;
        let fixture: ComponentFixture<CircuitBreakerTypeUpdateComponent>;
        let service: CircuitBreakerTypeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [CircuitBreakerTypeUpdateComponent]
            })
                .overrideTemplate(CircuitBreakerTypeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CircuitBreakerTypeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CircuitBreakerTypeService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new CircuitBreakerType(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.circuitBreakerType = entity;
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
                    const entity = new CircuitBreakerType();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.circuitBreakerType = entity;
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
