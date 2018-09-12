/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { DisconnectorDriveDeleteDialogComponent } from 'app/entities/disconnector-drive/disconnector-drive-delete-dialog.component';
import { DisconnectorDriveService } from 'app/entities/disconnector-drive/disconnector-drive.service';

describe('Component Tests', () => {
    describe('DisconnectorDrive Management Delete Component', () => {
        let comp: DisconnectorDriveDeleteDialogComponent;
        let fixture: ComponentFixture<DisconnectorDriveDeleteDialogComponent>;
        let service: DisconnectorDriveService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [DisconnectorDriveDeleteDialogComponent]
            })
                .overrideTemplate(DisconnectorDriveDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DisconnectorDriveDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DisconnectorDriveService);
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
