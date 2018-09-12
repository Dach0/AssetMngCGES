/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { TransformatorNumberDeleteDialogComponent } from 'app/entities/transformator-number/transformator-number-delete-dialog.component';
import { TransformatorNumberService } from 'app/entities/transformator-number/transformator-number.service';

describe('Component Tests', () => {
    describe('TransformatorNumber Management Delete Component', () => {
        let comp: TransformatorNumberDeleteDialogComponent;
        let fixture: ComponentFixture<TransformatorNumberDeleteDialogComponent>;
        let service: TransformatorNumberService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [TransformatorNumberDeleteDialogComponent]
            })
                .overrideTemplate(TransformatorNumberDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TransformatorNumberDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TransformatorNumberService);
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
