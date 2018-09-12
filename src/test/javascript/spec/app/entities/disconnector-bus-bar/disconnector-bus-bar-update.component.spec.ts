/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { DisconnectorBusBarUpdateComponent } from 'app/entities/disconnector-bus-bar/disconnector-bus-bar-update.component';
import { DisconnectorBusBarService } from 'app/entities/disconnector-bus-bar/disconnector-bus-bar.service';
import { DisconnectorBusBar } from 'app/shared/model/disconnector-bus-bar.model';

describe('Component Tests', () => {
    describe('DisconnectorBusBar Management Update Component', () => {
        let comp: DisconnectorBusBarUpdateComponent;
        let fixture: ComponentFixture<DisconnectorBusBarUpdateComponent>;
        let service: DisconnectorBusBarService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [DisconnectorBusBarUpdateComponent]
            })
                .overrideTemplate(DisconnectorBusBarUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DisconnectorBusBarUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DisconnectorBusBarService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new DisconnectorBusBar(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.disconnectorBusBar = entity;
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
                    const entity = new DisconnectorBusBar();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.disconnectorBusBar = entity;
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
