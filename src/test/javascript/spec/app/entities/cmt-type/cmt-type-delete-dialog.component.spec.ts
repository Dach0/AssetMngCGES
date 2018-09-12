/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { CmtTypeDeleteDialogComponent } from 'app/entities/cmt-type/cmt-type-delete-dialog.component';
import { CmtTypeService } from 'app/entities/cmt-type/cmt-type.service';

describe('Component Tests', () => {
    describe('CmtType Management Delete Component', () => {
        let comp: CmtTypeDeleteDialogComponent;
        let fixture: ComponentFixture<CmtTypeDeleteDialogComponent>;
        let service: CmtTypeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [CmtTypeDeleteDialogComponent]
            })
                .overrideTemplate(CmtTypeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CmtTypeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CmtTypeService);
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
