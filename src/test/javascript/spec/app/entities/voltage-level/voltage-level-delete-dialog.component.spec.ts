/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { VoltageLevelDeleteDialogComponent } from 'app/entities/voltage-level/voltage-level-delete-dialog.component';
import { VoltageLevelService } from 'app/entities/voltage-level/voltage-level.service';

describe('Component Tests', () => {
    describe('VoltageLevel Management Delete Component', () => {
        let comp: VoltageLevelDeleteDialogComponent;
        let fixture: ComponentFixture<VoltageLevelDeleteDialogComponent>;
        let service: VoltageLevelService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [VoltageLevelDeleteDialogComponent]
            })
                .overrideTemplate(VoltageLevelDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(VoltageLevelDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VoltageLevelService);
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
