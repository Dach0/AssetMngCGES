/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { CircuitBreakerDriveDeleteDialogComponent } from 'app/entities/circuit-breaker-drive/circuit-breaker-drive-delete-dialog.component';
import { CircuitBreakerDriveService } from 'app/entities/circuit-breaker-drive/circuit-breaker-drive.service';

describe('Component Tests', () => {
    describe('CircuitBreakerDrive Management Delete Component', () => {
        let comp: CircuitBreakerDriveDeleteDialogComponent;
        let fixture: ComponentFixture<CircuitBreakerDriveDeleteDialogComponent>;
        let service: CircuitBreakerDriveService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [CircuitBreakerDriveDeleteDialogComponent]
            })
                .overrideTemplate(CircuitBreakerDriveDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CircuitBreakerDriveDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CircuitBreakerDriveService);
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
