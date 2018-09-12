/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { ConductorCrossSectDeleteDialogComponent } from 'app/entities/conductor-cross-sect/conductor-cross-sect-delete-dialog.component';
import { ConductorCrossSectService } from 'app/entities/conductor-cross-sect/conductor-cross-sect.service';

describe('Component Tests', () => {
    describe('ConductorCrossSect Management Delete Component', () => {
        let comp: ConductorCrossSectDeleteDialogComponent;
        let fixture: ComponentFixture<ConductorCrossSectDeleteDialogComponent>;
        let service: ConductorCrossSectService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [ConductorCrossSectDeleteDialogComponent]
            })
                .overrideTemplate(ConductorCrossSectDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ConductorCrossSectDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConductorCrossSectService);
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
