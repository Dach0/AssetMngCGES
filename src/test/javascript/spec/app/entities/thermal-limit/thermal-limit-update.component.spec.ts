/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { ThermalLimitUpdateComponent } from 'app/entities/thermal-limit/thermal-limit-update.component';
import { ThermalLimitService } from 'app/entities/thermal-limit/thermal-limit.service';
import { ThermalLimit } from 'app/shared/model/thermal-limit.model';

describe('Component Tests', () => {
    describe('ThermalLimit Management Update Component', () => {
        let comp: ThermalLimitUpdateComponent;
        let fixture: ComponentFixture<ThermalLimitUpdateComponent>;
        let service: ThermalLimitService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [ThermalLimitUpdateComponent]
            })
                .overrideTemplate(ThermalLimitUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ThermalLimitUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ThermalLimitService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ThermalLimit(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.thermalLimit = entity;
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
                    const entity = new ThermalLimit();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.thermalLimit = entity;
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
