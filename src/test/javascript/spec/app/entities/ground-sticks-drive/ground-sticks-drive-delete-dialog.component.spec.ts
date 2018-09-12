/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { GroundSticksDriveDeleteDialogComponent } from 'app/entities/ground-sticks-drive/ground-sticks-drive-delete-dialog.component';
import { GroundSticksDriveService } from 'app/entities/ground-sticks-drive/ground-sticks-drive.service';

describe('Component Tests', () => {
    describe('GroundSticksDrive Management Delete Component', () => {
        let comp: GroundSticksDriveDeleteDialogComponent;
        let fixture: ComponentFixture<GroundSticksDriveDeleteDialogComponent>;
        let service: GroundSticksDriveService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [GroundSticksDriveDeleteDialogComponent]
            })
                .overrideTemplate(GroundSticksDriveDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GroundSticksDriveDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GroundSticksDriveService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
