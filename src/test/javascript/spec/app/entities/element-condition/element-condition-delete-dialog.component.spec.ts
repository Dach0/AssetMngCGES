/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { ElementConditionDeleteDialogComponent } from 'app/entities/element-condition/element-condition-delete-dialog.component';
import { ElementConditionService } from 'app/entities/element-condition/element-condition.service';

describe('Component Tests', () => {
    describe('ElementCondition Management Delete Component', () => {
        let comp: ElementConditionDeleteDialogComponent;
        let fixture: ComponentFixture<ElementConditionDeleteDialogComponent>;
        let service: ElementConditionService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [ElementConditionDeleteDialogComponent]
            })
                .overrideTemplate(ElementConditionDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ElementConditionDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ElementConditionService);
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
