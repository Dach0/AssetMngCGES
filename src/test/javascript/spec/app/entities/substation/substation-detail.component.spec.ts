/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { SubstationDetailComponent } from 'app/entities/substation/substation-detail.component';
import { Substation } from 'app/shared/model/substation.model';

describe('Component Tests', () => {
    describe('Substation Management Detail Component', () => {
        let comp: SubstationDetailComponent;
        let fixture: ComponentFixture<SubstationDetailComponent>;
        const route = ({ data: of({ substation: new Substation(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [SubstationDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SubstationDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SubstationDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.substation).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
