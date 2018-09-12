/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { EarthWireCrossSectUpdateComponent } from 'app/entities/earth-wire-cross-sect/earth-wire-cross-sect-update.component';
import { EarthWireCrossSectService } from 'app/entities/earth-wire-cross-sect/earth-wire-cross-sect.service';
import { EarthWireCrossSect } from 'app/shared/model/earth-wire-cross-sect.model';

describe('Component Tests', () => {
    describe('EarthWireCrossSect Management Update Component', () => {
        let comp: EarthWireCrossSectUpdateComponent;
        let fixture: ComponentFixture<EarthWireCrossSectUpdateComponent>;
        let service: EarthWireCrossSectService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [EarthWireCrossSectUpdateComponent]
            })
                .overrideTemplate(EarthWireCrossSectUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EarthWireCrossSectUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EarthWireCrossSectService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EarthWireCrossSect(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.earthWireCrossSect = entity;
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
                    const entity = new EarthWireCrossSect();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.earthWireCrossSect = entity;
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
