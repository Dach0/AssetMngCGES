/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { VoltageMeasuringTransformerUpdateComponent } from 'app/entities/voltage-measuring-transformer/voltage-measuring-transformer-update.component';
import { VoltageMeasuringTransformerService } from 'app/entities/voltage-measuring-transformer/voltage-measuring-transformer.service';
import { VoltageMeasuringTransformer } from 'app/shared/model/voltage-measuring-transformer.model';

describe('Component Tests', () => {
    describe('VoltageMeasuringTransformer Management Update Component', () => {
        let comp: VoltageMeasuringTransformerUpdateComponent;
        let fixture: ComponentFixture<VoltageMeasuringTransformerUpdateComponent>;
        let service: VoltageMeasuringTransformerService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [VoltageMeasuringTransformerUpdateComponent]
            })
                .overrideTemplate(VoltageMeasuringTransformerUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(VoltageMeasuringTransformerUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VoltageMeasuringTransformerService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new VoltageMeasuringTransformer(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.voltageMeasuringTransformer = entity;
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
                    const entity = new VoltageMeasuringTransformer();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.voltageMeasuringTransformer = entity;
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
