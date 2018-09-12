/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { VoltageMeasuringTransformerComponent } from 'app/entities/voltage-measuring-transformer/voltage-measuring-transformer.component';
import { VoltageMeasuringTransformerService } from 'app/entities/voltage-measuring-transformer/voltage-measuring-transformer.service';
import { VoltageMeasuringTransformer } from 'app/shared/model/voltage-measuring-transformer.model';

describe('Component Tests', () => {
    describe('VoltageMeasuringTransformer Management Component', () => {
        let comp: VoltageMeasuringTransformerComponent;
        let fixture: ComponentFixture<VoltageMeasuringTransformerComponent>;
        let service: VoltageMeasuringTransformerService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [VoltageMeasuringTransformerComponent],
                providers: []
            })
                .overrideTemplate(VoltageMeasuringTransformerComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(VoltageMeasuringTransformerComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VoltageMeasuringTransformerService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new VoltageMeasuringTransformer(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.voltageMeasuringTransformers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
