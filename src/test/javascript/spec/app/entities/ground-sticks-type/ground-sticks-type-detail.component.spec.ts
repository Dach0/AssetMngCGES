/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { GroundSticksTypeDetailComponent } from 'app/entities/ground-sticks-type/ground-sticks-type-detail.component';
import { GroundSticksType } from 'app/shared/model/ground-sticks-type.model';

describe('Component Tests', () => {
    describe('GroundSticksType Management Detail Component', () => {
        let comp: GroundSticksTypeDetailComponent;
        let fixture: ComponentFixture<GroundSticksTypeDetailComponent>;
        const route = ({ data: of({ groundSticksType: new GroundSticksType(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [GroundSticksTypeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(GroundSticksTypeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GroundSticksTypeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.groundSticksType).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
