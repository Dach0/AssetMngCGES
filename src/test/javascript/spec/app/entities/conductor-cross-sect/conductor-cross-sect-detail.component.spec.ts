/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { ConductorCrossSectDetailComponent } from 'app/entities/conductor-cross-sect/conductor-cross-sect-detail.component';
import { ConductorCrossSect } from 'app/shared/model/conductor-cross-sect.model';

describe('Component Tests', () => {
    describe('ConductorCrossSect Management Detail Component', () => {
        let comp: ConductorCrossSectDetailComponent;
        let fixture: ComponentFixture<ConductorCrossSectDetailComponent>;
        const route = ({ data: of({ conductorCrossSect: new ConductorCrossSect(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [ConductorCrossSectDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ConductorCrossSectDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ConductorCrossSectDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.conductorCrossSect).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
