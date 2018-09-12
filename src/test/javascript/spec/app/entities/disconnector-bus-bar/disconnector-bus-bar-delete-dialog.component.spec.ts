/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { DisconnectorBusBarDeleteDialogComponent } from 'app/entities/disconnector-bus-bar/disconnector-bus-bar-delete-dialog.component';
import { DisconnectorBusBarService } from 'app/entities/disconnector-bus-bar/disconnector-bus-bar.service';

describe('Component Tests', () => {
    describe('DisconnectorBusBar Management Delete Component', () => {
        let comp: DisconnectorBusBarDeleteDialogComponent;
        let fixture: ComponentFixture<DisconnectorBusBarDeleteDialogComponent>;
        let service: DisconnectorBusBarService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [DisconnectorBusBarDeleteDialogComponent]
            })
                .overrideTemplate(DisconnectorBusBarDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DisconnectorBusBarDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DisconnectorBusBarService);
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
