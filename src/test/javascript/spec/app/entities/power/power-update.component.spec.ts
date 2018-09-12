/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { PowerUpdateComponent } from 'app/entities/power/power-update.component';
import { PowerService } from 'app/entities/power/power.service';
import { Power } from 'app/shared/model/power.model';

describe('Component Tests', () => {
    describe('Power Management Update Component', () => {
        let comp: PowerUpdateComponent;
        let fixture: ComponentFixture<PowerUpdateComponent>;
        let service: PowerService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [PowerUpdateComponent]
            })
                .overrideTemplate(PowerUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PowerUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PowerService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Power(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.power = entity;
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
                    const entity = new Power();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.power = entity;
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
