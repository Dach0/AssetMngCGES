/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { AssetConditionUpdateComponent } from 'app/entities/asset-condition/asset-condition-update.component';
import { AssetConditionService } from 'app/entities/asset-condition/asset-condition.service';
import { AssetCondition } from 'app/shared/model/asset-condition.model';

describe('Component Tests', () => {
    describe('AssetCondition Management Update Component', () => {
        let comp: AssetConditionUpdateComponent;
        let fixture: ComponentFixture<AssetConditionUpdateComponent>;
        let service: AssetConditionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [AssetConditionUpdateComponent]
            })
                .overrideTemplate(AssetConditionUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AssetConditionUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AssetConditionService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new AssetCondition(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.assetCondition = entity;
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
                    const entity = new AssetCondition();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.assetCondition = entity;
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
