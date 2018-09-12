/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { ThermalLimitDetailComponent } from 'app/entities/thermal-limit/thermal-limit-detail.component';
import { ThermalLimit } from 'app/shared/model/thermal-limit.model';

describe('Component Tests', () => {
    describe('ThermalLimit Management Detail Component', () => {
        let comp: ThermalLimitDetailComponent;
        let fixture: ComponentFixture<ThermalLimitDetailComponent>;
        const route = ({ data: of({ thermalLimit: new ThermalLimit(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [ThermalLimitDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ThermalLimitDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ThermalLimitDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.thermalLimit).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
