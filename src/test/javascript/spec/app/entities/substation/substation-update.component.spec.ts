/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { SubstationUpdateComponent } from 'app/entities/substation/substation-update.component';
import { SubstationService } from 'app/entities/substation/substation.service';
import { Substation } from 'app/shared/model/substation.model';

describe('Component Tests', () => {
    describe('Substation Management Update Component', () => {
        let comp: SubstationUpdateComponent;
        let fixture: ComponentFixture<SubstationUpdateComponent>;
        let service: SubstationService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [SubstationUpdateComponent]
            })
                .overrideTemplate(SubstationUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SubstationUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SubstationService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Substation(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.substation = entity;
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
                    const entity = new Substation();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.substation = entity;
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
