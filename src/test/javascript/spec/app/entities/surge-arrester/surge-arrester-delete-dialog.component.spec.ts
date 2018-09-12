/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { SurgeArresterDeleteDialogComponent } from 'app/entities/surge-arrester/surge-arrester-delete-dialog.component';
import { SurgeArresterService } from 'app/entities/surge-arrester/surge-arrester.service';

describe('Component Tests', () => {
    describe('SurgeArrester Management Delete Component', () => {
        let comp: SurgeArresterDeleteDialogComponent;
        let fixture: ComponentFixture<SurgeArresterDeleteDialogComponent>;
        let service: SurgeArresterService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [SurgeArresterDeleteDialogComponent]
            })
                .overrideTemplate(SurgeArresterDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SurgeArresterDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SurgeArresterService);
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
