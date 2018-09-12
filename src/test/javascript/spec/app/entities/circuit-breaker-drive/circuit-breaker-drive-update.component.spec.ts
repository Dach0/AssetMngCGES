/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { CircuitBreakerDriveUpdateComponent } from 'app/entities/circuit-breaker-drive/circuit-breaker-drive-update.component';
import { CircuitBreakerDriveService } from 'app/entities/circuit-breaker-drive/circuit-breaker-drive.service';
import { CircuitBreakerDrive } from 'app/shared/model/circuit-breaker-drive.model';

describe('Component Tests', () => {
    describe('CircuitBreakerDrive Management Update Component', () => {
        let comp: CircuitBreakerDriveUpdateComponent;
        let fixture: ComponentFixture<CircuitBreakerDriveUpdateComponent>;
        let service: CircuitBreakerDriveService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [CircuitBreakerDriveUpdateComponent]
            })
                .overrideTemplate(CircuitBreakerDriveUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CircuitBreakerDriveUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CircuitBreakerDriveService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new CircuitBreakerDrive(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.circuitBreakerDrive = entity;
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
                    const entity = new CircuitBreakerDrive();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.circuitBreakerDrive = entity;
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
