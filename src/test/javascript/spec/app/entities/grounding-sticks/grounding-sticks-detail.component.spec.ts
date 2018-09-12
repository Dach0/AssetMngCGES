/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { GroundingSticksDetailComponent } from 'app/entities/grounding-sticks/grounding-sticks-detail.component';
import { GroundingSticks } from 'app/shared/model/grounding-sticks.model';

describe('Component Tests', () => {
    describe('GroundingSticks Management Detail Component', () => {
        let comp: GroundingSticksDetailComponent;
        let fixture: ComponentFixture<GroundingSticksDetailComponent>;
        const route = ({ data: of({ groundingSticks: new GroundingSticks(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [GroundingSticksDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(GroundingSticksDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GroundingSticksDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.groundingSticks).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
