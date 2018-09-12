/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { TransmissionRatioUpdateComponent } from 'app/entities/transmission-ratio/transmission-ratio-update.component';
import { TransmissionRatioService } from 'app/entities/transmission-ratio/transmission-ratio.service';
import { TransmissionRatio } from 'app/shared/model/transmission-ratio.model';

describe('Component Tests', () => {
    describe('TransmissionRatio Management Update Component', () => {
        let comp: TransmissionRatioUpdateComponent;
        let fixture: ComponentFixture<TransmissionRatioUpdateComponent>;
        let service: TransmissionRatioService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [TransmissionRatioUpdateComponent]
            })
                .overrideTemplate(TransmissionRatioUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TransmissionRatioUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TransmissionRatioService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new TransmissionRatio(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.transmissionRatio = entity;
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
                    const entity = new TransmissionRatio();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.transmissionRatio = entity;
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
