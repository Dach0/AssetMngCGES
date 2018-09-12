/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { VoltageLevelUpdateComponent } from 'app/entities/voltage-level/voltage-level-update.component';
import { VoltageLevelService } from 'app/entities/voltage-level/voltage-level.service';
import { VoltageLevel } from 'app/shared/model/voltage-level.model';

describe('Component Tests', () => {
    describe('VoltageLevel Management Update Component', () => {
        let comp: VoltageLevelUpdateComponent;
        let fixture: ComponentFixture<VoltageLevelUpdateComponent>;
        let service: VoltageLevelService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [VoltageLevelUpdateComponent]
            })
                .overrideTemplate(VoltageLevelUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(VoltageLevelUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VoltageLevelService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new VoltageLevel(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.voltageLevel = entity;
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
                    const entity = new VoltageLevel();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.voltageLevel = entity;
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
