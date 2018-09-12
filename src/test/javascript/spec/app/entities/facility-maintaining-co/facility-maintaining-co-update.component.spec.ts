/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { FacilityMaintainingCoUpdateComponent } from 'app/entities/facility-maintaining-co/facility-maintaining-co-update.component';
import { FacilityMaintainingCoService } from 'app/entities/facility-maintaining-co/facility-maintaining-co.service';
import { FacilityMaintainingCo } from 'app/shared/model/facility-maintaining-co.model';

describe('Component Tests', () => {
    describe('FacilityMaintainingCo Management Update Component', () => {
        let comp: FacilityMaintainingCoUpdateComponent;
        let fixture: ComponentFixture<FacilityMaintainingCoUpdateComponent>;
        let service: FacilityMaintainingCoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [FacilityMaintainingCoUpdateComponent]
            })
                .overrideTemplate(FacilityMaintainingCoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(FacilityMaintainingCoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FacilityMaintainingCoService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new FacilityMaintainingCo(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.facilityMaintainingCo = entity;
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
                    const entity = new FacilityMaintainingCo();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.facilityMaintainingCo = entity;
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
