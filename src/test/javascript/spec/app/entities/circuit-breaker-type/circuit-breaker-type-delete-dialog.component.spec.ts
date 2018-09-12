/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { CircuitBreakerTypeDeleteDialogComponent } from 'app/entities/circuit-breaker-type/circuit-breaker-type-delete-dialog.component';
import { CircuitBreakerTypeService } from 'app/entities/circuit-breaker-type/circuit-breaker-type.service';

describe('Component Tests', () => {
    describe('CircuitBreakerType Management Delete Component', () => {
        let comp: CircuitBreakerTypeDeleteDialogComponent;
        let fixture: ComponentFixture<CircuitBreakerTypeDeleteDialogComponent>;
        let service: CircuitBreakerTypeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [CircuitBreakerTypeDeleteDialogComponent]
            })
                .overrideTemplate(CircuitBreakerTypeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CircuitBreakerTypeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CircuitBreakerTypeService);
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
