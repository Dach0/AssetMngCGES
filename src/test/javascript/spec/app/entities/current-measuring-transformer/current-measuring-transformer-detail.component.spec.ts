/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { CurrentMeasuringTransformerDetailComponent } from 'app/entities/current-measuring-transformer/current-measuring-transformer-detail.component';
import { CurrentMeasuringTransformer } from 'app/shared/model/current-measuring-transformer.model';

describe('Component Tests', () => {
    describe('CurrentMeasuringTransformer Management Detail Component', () => {
        let comp: CurrentMeasuringTransformerDetailComponent;
        let fixture: ComponentFixture<CurrentMeasuringTransformerDetailComponent>;
        const route = ({ data: of({ currentMeasuringTransformer: new CurrentMeasuringTransformer(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [CurrentMeasuringTransformerDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CurrentMeasuringTransformerDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CurrentMeasuringTransformerDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.currentMeasuringTransformer).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
