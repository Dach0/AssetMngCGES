/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { DisconnectorTypeUpdateComponent } from 'app/entities/disconnector-type/disconnector-type-update.component';
import { DisconnectorTypeService } from 'app/entities/disconnector-type/disconnector-type.service';
import { DisconnectorType } from 'app/shared/model/disconnector-type.model';

describe('Component Tests', () => {
    describe('DisconnectorType Management Update Component', () => {
        let comp: DisconnectorTypeUpdateComponent;
        let fixture: ComponentFixture<DisconnectorTypeUpdateComponent>;
        let service: DisconnectorTypeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [DisconnectorTypeUpdateComponent]
            })
                .overrideTemplate(DisconnectorTypeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DisconnectorTypeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DisconnectorTypeService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new DisconnectorType(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.disconnectorType = entity;
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
                    const entity = new DisconnectorType();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.disconnectorType = entity;
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
