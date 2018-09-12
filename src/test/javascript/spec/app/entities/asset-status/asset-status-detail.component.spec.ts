/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { AssetStatusDetailComponent } from 'app/entities/asset-status/asset-status-detail.component';
import { AssetStatus } from 'app/shared/model/asset-status.model';

describe('Component Tests', () => {
    describe('AssetStatus Management Detail Component', () => {
        let comp: AssetStatusDetailComponent;
        let fixture: ComponentFixture<AssetStatusDetailComponent>;
        const route = ({ data: of({ assetStatus: new AssetStatus(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [AssetStatusDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AssetStatusDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AssetStatusDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.assetStatus).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
