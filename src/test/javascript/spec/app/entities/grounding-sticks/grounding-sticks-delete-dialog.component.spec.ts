/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { GroundingSticksDeleteDialogComponent } from 'app/entities/grounding-sticks/grounding-sticks-delete-dialog.component';
import { GroundingSticksService } from 'app/entities/grounding-sticks/grounding-sticks.service';

describe('Component Tests', () => {
    describe('GroundingSticks Management Delete Component', () => {
        let comp: GroundingSticksDeleteDialogComponent;
        let fixture: ComponentFixture<GroundingSticksDeleteDialogComponent>;
        let service: GroundingSticksService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [GroundingSticksDeleteDialogComponent]
            })
                .overrideTemplate(GroundingSticksDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GroundingSticksDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GroundingSticksService);
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
