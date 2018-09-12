/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { BoardOfDirectorsDeleteDialogComponent } from 'app/entities/board-of-directors/board-of-directors-delete-dialog.component';
import { BoardOfDirectorsService } from 'app/entities/board-of-directors/board-of-directors.service';

describe('Component Tests', () => {
    describe('BoardOfDirectors Management Delete Component', () => {
        let comp: BoardOfDirectorsDeleteDialogComponent;
        let fixture: ComponentFixture<BoardOfDirectorsDeleteDialogComponent>;
        let service: BoardOfDirectorsService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [BoardOfDirectorsDeleteDialogComponent]
            })
                .overrideTemplate(BoardOfDirectorsDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BoardOfDirectorsDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BoardOfDirectorsService);
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
