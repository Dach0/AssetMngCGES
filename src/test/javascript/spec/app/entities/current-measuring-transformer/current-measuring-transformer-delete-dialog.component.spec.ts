/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { CurrentMeasuringTransformerDeleteDialogComponent } from 'app/entities/current-measuring-transformer/current-measuring-transformer-delete-dialog.component';
import { CurrentMeasuringTransformerService } from 'app/entities/current-measuring-transformer/current-measuring-transformer.service';

describe('Component Tests', () => {
    describe('CurrentMeasuringTransformer Management Delete Component', () => {
        let comp: CurrentMeasuringTransformerDeleteDialogComponent;
        let fixture: ComponentFixture<CurrentMeasuringTransformerDeleteDialogComponent>;
        let service: CurrentMeasuringTransformerService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [CurrentMeasuringTransformerDeleteDialogComponent]
            })
                .overrideTemplate(CurrentMeasuringTransformerDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CurrentMeasuringTransformerDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CurrentMeasuringTransformerService);
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
