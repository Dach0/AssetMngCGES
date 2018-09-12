/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { QualificationUpdateComponent } from 'app/entities/qualification/qualification-update.component';
import { QualificationService } from 'app/entities/qualification/qualification.service';
import { Qualification } from 'app/shared/model/qualification.model';

describe('Component Tests', () => {
    describe('Qualification Management Update Component', () => {
        let comp: QualificationUpdateComponent;
        let fixture: ComponentFixture<QualificationUpdateComponent>;
        let service: QualificationService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [QualificationUpdateComponent]
            })
                .overrideTemplate(QualificationUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(QualificationUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(QualificationService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Qualification(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.qualification = entity;
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
                    const entity = new Qualification();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.qualification = entity;
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
