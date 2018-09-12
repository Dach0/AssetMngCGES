/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { TransformerTypeDeleteDialogComponent } from 'app/entities/transformer-type/transformer-type-delete-dialog.component';
import { TransformerTypeService } from 'app/entities/transformer-type/transformer-type.service';

describe('Component Tests', () => {
    describe('TransformerType Management Delete Component', () => {
        let comp: TransformerTypeDeleteDialogComponent;
        let fixture: ComponentFixture<TransformerTypeDeleteDialogComponent>;
        let service: TransformerTypeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [TransformerTypeDeleteDialogComponent]
            })
                .overrideTemplate(TransformerTypeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TransformerTypeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TransformerTypeService);
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
