/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { GroundingSticksUpdateComponent } from 'app/entities/grounding-sticks/grounding-sticks-update.component';
import { GroundingSticksService } from 'app/entities/grounding-sticks/grounding-sticks.service';
import { GroundingSticks } from 'app/shared/model/grounding-sticks.model';

describe('Component Tests', () => {
    describe('GroundingSticks Management Update Component', () => {
        let comp: GroundingSticksUpdateComponent;
        let fixture: ComponentFixture<GroundingSticksUpdateComponent>;
        let service: GroundingSticksService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [GroundingSticksUpdateComponent]
            })
                .overrideTemplate(GroundingSticksUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(GroundingSticksUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GroundingSticksService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new GroundingSticks(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.groundingSticks = entity;
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
                    const entity = new GroundingSticks();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.groundingSticks = entity;
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
