/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { SurgeArresterTypeUpdateComponent } from 'app/entities/surge-arrester-type/surge-arrester-type-update.component';
import { SurgeArresterTypeService } from 'app/entities/surge-arrester-type/surge-arrester-type.service';
import { SurgeArresterType } from 'app/shared/model/surge-arrester-type.model';

describe('Component Tests', () => {
    describe('SurgeArresterType Management Update Component', () => {
        let comp: SurgeArresterTypeUpdateComponent;
        let fixture: ComponentFixture<SurgeArresterTypeUpdateComponent>;
        let service: SurgeArresterTypeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [SurgeArresterTypeUpdateComponent]
            })
                .overrideTemplate(SurgeArresterTypeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SurgeArresterTypeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SurgeArresterTypeService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new SurgeArresterType(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.surgeArresterType = entity;
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
                    const entity = new SurgeArresterType();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.surgeArresterType = entity;
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
