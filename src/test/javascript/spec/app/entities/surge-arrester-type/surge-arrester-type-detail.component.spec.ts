/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { SurgeArresterTypeDetailComponent } from 'app/entities/surge-arrester-type/surge-arrester-type-detail.component';
import { SurgeArresterType } from 'app/shared/model/surge-arrester-type.model';

describe('Component Tests', () => {
    describe('SurgeArresterType Management Detail Component', () => {
        let comp: SurgeArresterTypeDetailComponent;
        let fixture: ComponentFixture<SurgeArresterTypeDetailComponent>;
        const route = ({ data: of({ surgeArresterType: new SurgeArresterType(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [SurgeArresterTypeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SurgeArresterTypeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SurgeArresterTypeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.surgeArresterType).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
