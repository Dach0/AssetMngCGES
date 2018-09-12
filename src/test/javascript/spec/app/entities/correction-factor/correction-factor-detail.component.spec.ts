/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { CorrectionFactorDetailComponent } from 'app/entities/correction-factor/correction-factor-detail.component';
import { CorrectionFactor } from 'app/shared/model/correction-factor.model';

describe('Component Tests', () => {
    describe('CorrectionFactor Management Detail Component', () => {
        let comp: CorrectionFactorDetailComponent;
        let fixture: ComponentFixture<CorrectionFactorDetailComponent>;
        const route = ({ data: of({ correctionFactor: new CorrectionFactor(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [CorrectionFactorDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CorrectionFactorDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CorrectionFactorDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.correctionFactor).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
