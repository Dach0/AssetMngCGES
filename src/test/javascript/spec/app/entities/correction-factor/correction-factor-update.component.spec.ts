/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { CorrectionFactorUpdateComponent } from 'app/entities/correction-factor/correction-factor-update.component';
import { CorrectionFactorService } from 'app/entities/correction-factor/correction-factor.service';
import { CorrectionFactor } from 'app/shared/model/correction-factor.model';

describe('Component Tests', () => {
    describe('CorrectionFactor Management Update Component', () => {
        let comp: CorrectionFactorUpdateComponent;
        let fixture: ComponentFixture<CorrectionFactorUpdateComponent>;
        let service: CorrectionFactorService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [CorrectionFactorUpdateComponent]
            })
                .overrideTemplate(CorrectionFactorUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CorrectionFactorUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CorrectionFactorService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new CorrectionFactor(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.correctionFactor = entity;
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
                    const entity = new CorrectionFactor();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.correctionFactor = entity;
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
