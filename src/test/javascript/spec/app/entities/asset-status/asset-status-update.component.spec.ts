/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { AssetStatusUpdateComponent } from 'app/entities/asset-status/asset-status-update.component';
import { AssetStatusService } from 'app/entities/asset-status/asset-status.service';
import { AssetStatus } from 'app/shared/model/asset-status.model';

describe('Component Tests', () => {
    describe('AssetStatus Management Update Component', () => {
        let comp: AssetStatusUpdateComponent;
        let fixture: ComponentFixture<AssetStatusUpdateComponent>;
        let service: AssetStatusService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [AssetStatusUpdateComponent]
            })
                .overrideTemplate(AssetStatusUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AssetStatusUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AssetStatusService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new AssetStatus(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.assetStatus = entity;
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
                    const entity = new AssetStatus();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.assetStatus = entity;
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
