/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { TransmissionRatioDetailComponent } from 'app/entities/transmission-ratio/transmission-ratio-detail.component';
import { TransmissionRatio } from 'app/shared/model/transmission-ratio.model';

describe('Component Tests', () => {
    describe('TransmissionRatio Management Detail Component', () => {
        let comp: TransmissionRatioDetailComponent;
        let fixture: ComponentFixture<TransmissionRatioDetailComponent>;
        const route = ({ data: of({ transmissionRatio: new TransmissionRatio(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [TransmissionRatioDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TransmissionRatioDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TransmissionRatioDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.transmissionRatio).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
