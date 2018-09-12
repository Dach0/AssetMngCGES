/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { ElementConditionUpdateComponent } from 'app/entities/element-condition/element-condition-update.component';
import { ElementConditionService } from 'app/entities/element-condition/element-condition.service';
import { ElementCondition } from 'app/shared/model/element-condition.model';

describe('Component Tests', () => {
    describe('ElementCondition Management Update Component', () => {
        let comp: ElementConditionUpdateComponent;
        let fixture: ComponentFixture<ElementConditionUpdateComponent>;
        let service: ElementConditionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [ElementConditionUpdateComponent]
            })
                .overrideTemplate(ElementConditionUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ElementConditionUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ElementConditionService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ElementCondition(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.elementCondition = entity;
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
                    const entity = new ElementCondition();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.elementCondition = entity;
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
