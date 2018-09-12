/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { TransmissionRatioDeleteDialogComponent } from 'app/entities/transmission-ratio/transmission-ratio-delete-dialog.component';
import { TransmissionRatioService } from 'app/entities/transmission-ratio/transmission-ratio.service';

describe('Component Tests', () => {
    describe('TransmissionRatio Management Delete Component', () => {
        let comp: TransmissionRatioDeleteDialogComponent;
        let fixture: ComponentFixture<TransmissionRatioDeleteDialogComponent>;
        let service: TransmissionRatioService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [TransmissionRatioDeleteDialogComponent]
            })
                .overrideTemplate(TransmissionRatioDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TransmissionRatioDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TransmissionRatioService);
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
