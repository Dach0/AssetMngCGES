/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { SurgeArresterUpdateComponent } from 'app/entities/surge-arrester/surge-arrester-update.component';
import { SurgeArresterService } from 'app/entities/surge-arrester/surge-arrester.service';
import { SurgeArrester } from 'app/shared/model/surge-arrester.model';

describe('Component Tests', () => {
    describe('SurgeArrester Management Update Component', () => {
        let comp: SurgeArresterUpdateComponent;
        let fixture: ComponentFixture<SurgeArresterUpdateComponent>;
        let service: SurgeArresterService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [SurgeArresterUpdateComponent]
            })
                .overrideTemplate(SurgeArresterUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SurgeArresterUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SurgeArresterService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new SurgeArrester(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.surgeArrester = entity;
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
                    const entity = new SurgeArrester();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.surgeArrester = entity;
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
