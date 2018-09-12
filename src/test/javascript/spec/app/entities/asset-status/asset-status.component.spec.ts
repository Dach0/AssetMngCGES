/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { AssetStatusComponent } from 'app/entities/asset-status/asset-status.component';
import { AssetStatusService } from 'app/entities/asset-status/asset-status.service';
import { AssetStatus } from 'app/shared/model/asset-status.model';

describe('Component Tests', () => {
    describe('AssetStatus Management Component', () => {
        let comp: AssetStatusComponent;
        let fixture: ComponentFixture<AssetStatusComponent>;
        let service: AssetStatusService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [AssetStatusComponent],
                providers: []
            })
                .overrideTemplate(AssetStatusComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AssetStatusComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AssetStatusService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new AssetStatus(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.assetStatuses[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
