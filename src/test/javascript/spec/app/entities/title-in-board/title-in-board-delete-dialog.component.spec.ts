/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { TitleInBoardDeleteDialogComponent } from 'app/entities/title-in-board/title-in-board-delete-dialog.component';
import { TitleInBoardService } from 'app/entities/title-in-board/title-in-board.service';

describe('Component Tests', () => {
    describe('TitleInBoard Management Delete Component', () => {
        let comp: TitleInBoardDeleteDialogComponent;
        let fixture: ComponentFixture<TitleInBoardDeleteDialogComponent>;
        let service: TitleInBoardService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [TitleInBoardDeleteDialogComponent]
            })
                .overrideTemplate(TitleInBoardDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TitleInBoardDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TitleInBoardService);
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
