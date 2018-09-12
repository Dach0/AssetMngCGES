/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { DisconnectorLineExitUpdateComponent } from 'app/entities/disconnector-line-exit/disconnector-line-exit-update.component';
import { DisconnectorLineExitService } from 'app/entities/disconnector-line-exit/disconnector-line-exit.service';
import { DisconnectorLineExit } from 'app/shared/model/disconnector-line-exit.model';

describe('Component Tests', () => {
    describe('DisconnectorLineExit Management Update Component', () => {
        let comp: DisconnectorLineExitUpdateComponent;
        let fixture: ComponentFixture<DisconnectorLineExitUpdateComponent>;
        let service: DisconnectorLineExitService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [DisconnectorLineExitUpdateComponent]
            })
                .overrideTemplate(DisconnectorLineExitUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DisconnectorLineExitUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DisconnectorLineExitService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new DisconnectorLineExit(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.disconnectorLineExit = entity;
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
                    const entity = new DisconnectorLineExit();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.disconnectorLineExit = entity;
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
