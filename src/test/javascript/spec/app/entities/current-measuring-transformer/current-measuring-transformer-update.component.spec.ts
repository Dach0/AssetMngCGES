/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { CurrentMeasuringTransformerUpdateComponent } from 'app/entities/current-measuring-transformer/current-measuring-transformer-update.component';
import { CurrentMeasuringTransformerService } from 'app/entities/current-measuring-transformer/current-measuring-transformer.service';
import { CurrentMeasuringTransformer } from 'app/shared/model/current-measuring-transformer.model';

describe('Component Tests', () => {
    describe('CurrentMeasuringTransformer Management Update Component', () => {
        let comp: CurrentMeasuringTransformerUpdateComponent;
        let fixture: ComponentFixture<CurrentMeasuringTransformerUpdateComponent>;
        let service: CurrentMeasuringTransformerService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [CurrentMeasuringTransformerUpdateComponent]
            })
                .overrideTemplate(CurrentMeasuringTransformerUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CurrentMeasuringTransformerUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CurrentMeasuringTransformerService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new CurrentMeasuringTransformer(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.currentMeasuringTransformer = entity;
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
                    const entity = new CurrentMeasuringTransformer();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.currentMeasuringTransformer = entity;
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
