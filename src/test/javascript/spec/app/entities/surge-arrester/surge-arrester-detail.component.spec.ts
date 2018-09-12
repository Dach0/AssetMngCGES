/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { SurgeArresterDetailComponent } from 'app/entities/surge-arrester/surge-arrester-detail.component';
import { SurgeArrester } from 'app/shared/model/surge-arrester.model';

describe('Component Tests', () => {
    describe('SurgeArrester Management Detail Component', () => {
        let comp: SurgeArresterDetailComponent;
        let fixture: ComponentFixture<SurgeArresterDetailComponent>;
        const route = ({ data: of({ surgeArrester: new SurgeArrester(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [SurgeArresterDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SurgeArresterDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SurgeArresterDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.surgeArrester).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
