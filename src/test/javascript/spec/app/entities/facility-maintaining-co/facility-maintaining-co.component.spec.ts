/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { FacilityMaintainingCoComponent } from 'app/entities/facility-maintaining-co/facility-maintaining-co.component';
import { FacilityMaintainingCoService } from 'app/entities/facility-maintaining-co/facility-maintaining-co.service';
import { FacilityMaintainingCo } from 'app/shared/model/facility-maintaining-co.model';

describe('Component Tests', () => {
    describe('FacilityMaintainingCo Management Component', () => {
        let comp: FacilityMaintainingCoComponent;
        let fixture: ComponentFixture<FacilityMaintainingCoComponent>;
        let service: FacilityMaintainingCoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [FacilityMaintainingCoComponent],
                providers: []
            })
                .overrideTemplate(FacilityMaintainingCoComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(FacilityMaintainingCoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FacilityMaintainingCoService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new FacilityMaintainingCo(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.facilityMaintainingCos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
