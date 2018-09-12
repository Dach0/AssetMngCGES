/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { TransformatorNumberDetailComponent } from 'app/entities/transformator-number/transformator-number-detail.component';
import { TransformatorNumber } from 'app/shared/model/transformator-number.model';

describe('Component Tests', () => {
    describe('TransformatorNumber Management Detail Component', () => {
        let comp: TransformatorNumberDetailComponent;
        let fixture: ComponentFixture<TransformatorNumberDetailComponent>;
        const route = ({ data: of({ transformatorNumber: new TransformatorNumber(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [TransformatorNumberDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TransformatorNumberDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TransformatorNumberDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.transformatorNumber).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
