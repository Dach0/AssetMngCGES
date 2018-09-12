/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { CorrectionFactorComponent } from 'app/entities/correction-factor/correction-factor.component';
import { CorrectionFactorService } from 'app/entities/correction-factor/correction-factor.service';
import { CorrectionFactor } from 'app/shared/model/correction-factor.model';

describe('Component Tests', () => {
    describe('CorrectionFactor Management Component', () => {
        let comp: CorrectionFactorComponent;
        let fixture: ComponentFixture<CorrectionFactorComponent>;
        let service: CorrectionFactorService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [CorrectionFactorComponent],
                providers: []
            })
                .overrideTemplate(CorrectionFactorComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CorrectionFactorComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CorrectionFactorService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new CorrectionFactor(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.correctionFactors[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
