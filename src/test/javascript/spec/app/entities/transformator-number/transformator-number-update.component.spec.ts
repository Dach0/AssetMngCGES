/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { TransformatorNumberUpdateComponent } from 'app/entities/transformator-number/transformator-number-update.component';
import { TransformatorNumberService } from 'app/entities/transformator-number/transformator-number.service';
import { TransformatorNumber } from 'app/shared/model/transformator-number.model';

describe('Component Tests', () => {
    describe('TransformatorNumber Management Update Component', () => {
        let comp: TransformatorNumberUpdateComponent;
        let fixture: ComponentFixture<TransformatorNumberUpdateComponent>;
        let service: TransformatorNumberService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [TransformatorNumberUpdateComponent]
            })
                .overrideTemplate(TransformatorNumberUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TransformatorNumberUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TransformatorNumberService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new TransformatorNumber(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.transformatorNumber = entity;
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
                    const entity = new TransformatorNumber();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.transformatorNumber = entity;
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
