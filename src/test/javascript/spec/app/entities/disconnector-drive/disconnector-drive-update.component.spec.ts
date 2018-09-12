/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { DisconnectorDriveUpdateComponent } from 'app/entities/disconnector-drive/disconnector-drive-update.component';
import { DisconnectorDriveService } from 'app/entities/disconnector-drive/disconnector-drive.service';
import { DisconnectorDrive } from 'app/shared/model/disconnector-drive.model';

describe('Component Tests', () => {
    describe('DisconnectorDrive Management Update Component', () => {
        let comp: DisconnectorDriveUpdateComponent;
        let fixture: ComponentFixture<DisconnectorDriveUpdateComponent>;
        let service: DisconnectorDriveService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [DisconnectorDriveUpdateComponent]
            })
                .overrideTemplate(DisconnectorDriveUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DisconnectorDriveUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DisconnectorDriveService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new DisconnectorDrive(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.disconnectorDrive = entity;
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
                    const entity = new DisconnectorDrive();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.disconnectorDrive = entity;
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
