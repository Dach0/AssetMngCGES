/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { TconnectionUpdateComponent } from 'app/entities/tconnection/tconnection-update.component';
import { TconnectionService } from 'app/entities/tconnection/tconnection.service';
import { Tconnection } from 'app/shared/model/tconnection.model';

describe('Component Tests', () => {
    describe('Tconnection Management Update Component', () => {
        let comp: TconnectionUpdateComponent;
        let fixture: ComponentFixture<TconnectionUpdateComponent>;
        let service: TconnectionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [TconnectionUpdateComponent]
            })
                .overrideTemplate(TconnectionUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TconnectionUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TconnectionService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Tconnection(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.tconnection = entity;
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
                    const entity = new Tconnection();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.tconnection = entity;
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
