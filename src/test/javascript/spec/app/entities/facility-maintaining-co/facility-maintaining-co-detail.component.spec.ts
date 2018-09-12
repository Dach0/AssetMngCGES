/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { FacilityMaintainingCoDetailComponent } from 'app/entities/facility-maintaining-co/facility-maintaining-co-detail.component';
import { FacilityMaintainingCo } from 'app/shared/model/facility-maintaining-co.model';

describe('Component Tests', () => {
    describe('FacilityMaintainingCo Management Detail Component', () => {
        let comp: FacilityMaintainingCoDetailComponent;
        let fixture: ComponentFixture<FacilityMaintainingCoDetailComponent>;
        const route = ({ data: of({ facilityMaintainingCo: new FacilityMaintainingCo(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [FacilityMaintainingCoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(FacilityMaintainingCoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FacilityMaintainingCoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.facilityMaintainingCo).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
