/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { PhonePrivilageUpdateComponent } from 'app/entities/phone-privilage/phone-privilage-update.component';
import { PhonePrivilageService } from 'app/entities/phone-privilage/phone-privilage.service';
import { PhonePrivilage } from 'app/shared/model/phone-privilage.model';

describe('Component Tests', () => {
    describe('PhonePrivilage Management Update Component', () => {
        let comp: PhonePrivilageUpdateComponent;
        let fixture: ComponentFixture<PhonePrivilageUpdateComponent>;
        let service: PhonePrivilageService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [PhonePrivilageUpdateComponent]
            })
                .overrideTemplate(PhonePrivilageUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PhonePrivilageUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PhonePrivilageService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new PhonePrivilage(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.phonePrivilage = entity;
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
                    const entity = new PhonePrivilage();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.phonePrivilage = entity;
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
