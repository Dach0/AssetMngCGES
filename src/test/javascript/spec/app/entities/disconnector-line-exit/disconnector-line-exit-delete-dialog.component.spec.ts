/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { DisconnectorLineExitDeleteDialogComponent } from 'app/entities/disconnector-line-exit/disconnector-line-exit-delete-dialog.component';
import { DisconnectorLineExitService } from 'app/entities/disconnector-line-exit/disconnector-line-exit.service';

describe('Component Tests', () => {
    describe('DisconnectorLineExit Management Delete Component', () => {
        let comp: DisconnectorLineExitDeleteDialogComponent;
        let fixture: ComponentFixture<DisconnectorLineExitDeleteDialogComponent>;
        let service: DisconnectorLineExitService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [DisconnectorLineExitDeleteDialogComponent]
            })
                .overrideTemplate(DisconnectorLineExitDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DisconnectorLineExitDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DisconnectorLineExitService);
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
