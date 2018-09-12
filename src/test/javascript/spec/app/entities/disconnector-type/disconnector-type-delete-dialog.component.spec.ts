/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { DisconnectorTypeDeleteDialogComponent } from 'app/entities/disconnector-type/disconnector-type-delete-dialog.component';
import { DisconnectorTypeService } from 'app/entities/disconnector-type/disconnector-type.service';

describe('Component Tests', () => {
    describe('DisconnectorType Management Delete Component', () => {
        let comp: DisconnectorTypeDeleteDialogComponent;
        let fixture: ComponentFixture<DisconnectorTypeDeleteDialogComponent>;
        let service: DisconnectorTypeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [DisconnectorTypeDeleteDialogComponent]
            })
                .overrideTemplate(DisconnectorTypeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DisconnectorTypeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DisconnectorTypeService);
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
