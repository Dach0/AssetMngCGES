/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { GroundSticksTypeUpdateComponent } from 'app/entities/ground-sticks-type/ground-sticks-type-update.component';
import { GroundSticksTypeService } from 'app/entities/ground-sticks-type/ground-sticks-type.service';
import { GroundSticksType } from 'app/shared/model/ground-sticks-type.model';

describe('Component Tests', () => {
    describe('GroundSticksType Management Update Component', () => {
        let comp: GroundSticksTypeUpdateComponent;
        let fixture: ComponentFixture<GroundSticksTypeUpdateComponent>;
        let service: GroundSticksTypeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [GroundSticksTypeUpdateComponent]
            })
                .overrideTemplate(GroundSticksTypeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(GroundSticksTypeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GroundSticksTypeService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new GroundSticksType(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.groundSticksType = entity;
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
                    const entity = new GroundSticksType();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.groundSticksType = entity;
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
