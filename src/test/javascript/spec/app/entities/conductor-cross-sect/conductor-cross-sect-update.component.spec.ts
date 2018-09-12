/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { ConductorCrossSectUpdateComponent } from 'app/entities/conductor-cross-sect/conductor-cross-sect-update.component';
import { ConductorCrossSectService } from 'app/entities/conductor-cross-sect/conductor-cross-sect.service';
import { ConductorCrossSect } from 'app/shared/model/conductor-cross-sect.model';

describe('Component Tests', () => {
    describe('ConductorCrossSect Management Update Component', () => {
        let comp: ConductorCrossSectUpdateComponent;
        let fixture: ComponentFixture<ConductorCrossSectUpdateComponent>;
        let service: ConductorCrossSectService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [ConductorCrossSectUpdateComponent]
            })
                .overrideTemplate(ConductorCrossSectUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ConductorCrossSectUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConductorCrossSectService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ConductorCrossSect(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.conductorCrossSect = entity;
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
                    const entity = new ConductorCrossSect();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.conductorCrossSect = entity;
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
