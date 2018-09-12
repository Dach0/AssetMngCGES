/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { VoltageMeasuringTransformerDetailComponent } from 'app/entities/voltage-measuring-transformer/voltage-measuring-transformer-detail.component';
import { VoltageMeasuringTransformer } from 'app/shared/model/voltage-measuring-transformer.model';

describe('Component Tests', () => {
    describe('VoltageMeasuringTransformer Management Detail Component', () => {
        let comp: VoltageMeasuringTransformerDetailComponent;
        let fixture: ComponentFixture<VoltageMeasuringTransformerDetailComponent>;
        const route = ({ data: of({ voltageMeasuringTransformer: new VoltageMeasuringTransformer(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [VoltageMeasuringTransformerDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(VoltageMeasuringTransformerDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(VoltageMeasuringTransformerDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.voltageMeasuringTransformer).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
