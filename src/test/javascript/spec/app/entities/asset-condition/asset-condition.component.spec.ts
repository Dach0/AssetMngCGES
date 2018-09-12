/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { AssetConditionComponent } from 'app/entities/asset-condition/asset-condition.component';
import { AssetConditionService } from 'app/entities/asset-condition/asset-condition.service';
import { AssetCondition } from 'app/shared/model/asset-condition.model';

describe('Component Tests', () => {
    describe('AssetCondition Management Component', () => {
        let comp: AssetConditionComponent;
        let fixture: ComponentFixture<AssetConditionComponent>;
        let service: AssetConditionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [AssetConditionComponent],
                providers: []
            })
                .overrideTemplate(AssetConditionComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AssetConditionComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AssetConditionService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new AssetCondition(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.assetConditions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
