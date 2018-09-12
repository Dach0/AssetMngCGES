/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { CouplingUpdateComponent } from 'app/entities/coupling/coupling-update.component';
import { CouplingService } from 'app/entities/coupling/coupling.service';
import { Coupling } from 'app/shared/model/coupling.model';

describe('Component Tests', () => {
    describe('Coupling Management Update Component', () => {
        let comp: CouplingUpdateComponent;
        let fixture: ComponentFixture<CouplingUpdateComponent>;
        let service: CouplingService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [CouplingUpdateComponent]
            })
                .overrideTemplate(CouplingUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CouplingUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CouplingService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Coupling(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.coupling = entity;
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
                    const entity = new Coupling();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.coupling = entity;
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
