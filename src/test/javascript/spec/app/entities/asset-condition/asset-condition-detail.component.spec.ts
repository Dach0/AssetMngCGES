/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { AssetConditionDetailComponent } from 'app/entities/asset-condition/asset-condition-detail.component';
import { AssetCondition } from 'app/shared/model/asset-condition.model';

describe('Component Tests', () => {
    describe('AssetCondition Management Detail Component', () => {
        let comp: AssetConditionDetailComponent;
        let fixture: ComponentFixture<AssetConditionDetailComponent>;
        const route = ({ data: of({ assetCondition: new AssetCondition(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [AssetConditionDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AssetConditionDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AssetConditionDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.assetCondition).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
