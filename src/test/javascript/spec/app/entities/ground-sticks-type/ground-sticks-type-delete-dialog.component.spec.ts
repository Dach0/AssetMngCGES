/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { GroundSticksTypeDeleteDialogComponent } from 'app/entities/ground-sticks-type/ground-sticks-type-delete-dialog.component';
import { GroundSticksTypeService } from 'app/entities/ground-sticks-type/ground-sticks-type.service';

describe('Component Tests', () => {
    describe('GroundSticksType Management Delete Component', () => {
        let comp: GroundSticksTypeDeleteDialogComponent;
        let fixture: ComponentFixture<GroundSticksTypeDeleteDialogComponent>;
        let service: GroundSticksTypeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [GroundSticksTypeDeleteDialogComponent]
            })
                .overrideTemplate(GroundSticksTypeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GroundSticksTypeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GroundSticksTypeService);
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
