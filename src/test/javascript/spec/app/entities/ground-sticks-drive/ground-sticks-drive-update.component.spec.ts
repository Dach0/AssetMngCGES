/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { GroundSticksDriveUpdateComponent } from 'app/entities/ground-sticks-drive/ground-sticks-drive-update.component';
import { GroundSticksDriveService } from 'app/entities/ground-sticks-drive/ground-sticks-drive.service';
import { GroundSticksDrive } from 'app/shared/model/ground-sticks-drive.model';

describe('Component Tests', () => {
    describe('GroundSticksDrive Management Update Component', () => {
        let comp: GroundSticksDriveUpdateComponent;
        let fixture: ComponentFixture<GroundSticksDriveUpdateComponent>;
        let service: GroundSticksDriveService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [GroundSticksDriveUpdateComponent]
            })
                .overrideTemplate(GroundSticksDriveUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(GroundSticksDriveUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GroundSticksDriveService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new GroundSticksDrive(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.groundSticksDrive = entity;
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
                    const entity = new GroundSticksDrive();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.groundSticksDrive = entity;
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
