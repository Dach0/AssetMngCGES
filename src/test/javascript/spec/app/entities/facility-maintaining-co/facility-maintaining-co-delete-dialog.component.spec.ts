/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { FacilityMaintainingCoDeleteDialogComponent } from 'app/entities/facility-maintaining-co/facility-maintaining-co-delete-dialog.component';
import { FacilityMaintainingCoService } from 'app/entities/facility-maintaining-co/facility-maintaining-co.service';

describe('Component Tests', () => {
    describe('FacilityMaintainingCo Management Delete Component', () => {
        let comp: FacilityMaintainingCoDeleteDialogComponent;
        let fixture: ComponentFixture<FacilityMaintainingCoDeleteDialogComponent>;
        let service: FacilityMaintainingCoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [FacilityMaintainingCoDeleteDialogComponent]
            })
                .overrideTemplate(FacilityMaintainingCoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FacilityMaintainingCoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FacilityMaintainingCoService);
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
