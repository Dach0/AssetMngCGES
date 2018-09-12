/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { OhlUpdateComponent } from 'app/entities/ohl/ohl-update.component';
import { OhlService } from 'app/entities/ohl/ohl.service';
import { Ohl } from 'app/shared/model/ohl.model';

describe('Component Tests', () => {
    describe('Ohl Management Update Component', () => {
        let comp: OhlUpdateComponent;
        let fixture: ComponentFixture<OhlUpdateComponent>;
        let service: OhlService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [OhlUpdateComponent]
            })
                .overrideTemplate(OhlUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(OhlUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OhlService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Ohl(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.ohl = entity;
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
                    const entity = new Ohl();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.ohl = entity;
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
