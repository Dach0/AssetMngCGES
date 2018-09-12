/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { TconnectionDeleteDialogComponent } from 'app/entities/tconnection/tconnection-delete-dialog.component';
import { TconnectionService } from 'app/entities/tconnection/tconnection.service';

describe('Component Tests', () => {
    describe('Tconnection Management Delete Component', () => {
        let comp: TconnectionDeleteDialogComponent;
        let fixture: ComponentFixture<TconnectionDeleteDialogComponent>;
        let service: TconnectionService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [TconnectionDeleteDialogComponent]
            })
                .overrideTemplate(TconnectionDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TconnectionDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TconnectionService);
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
