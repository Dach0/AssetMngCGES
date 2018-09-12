/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { CurrentMeasuringTransformerComponent } from 'app/entities/current-measuring-transformer/current-measuring-transformer.component';
import { CurrentMeasuringTransformerService } from 'app/entities/current-measuring-transformer/current-measuring-transformer.service';
import { CurrentMeasuringTransformer } from 'app/shared/model/current-measuring-transformer.model';

describe('Component Tests', () => {
    describe('CurrentMeasuringTransformer Management Component', () => {
        let comp: CurrentMeasuringTransformerComponent;
        let fixture: ComponentFixture<CurrentMeasuringTransformerComponent>;
        let service: CurrentMeasuringTransformerService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [CurrentMeasuringTransformerComponent],
                providers: []
            })
                .overrideTemplate(CurrentMeasuringTransformerComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CurrentMeasuringTransformerComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CurrentMeasuringTransformerService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new CurrentMeasuringTransformer(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.currentMeasuringTransformers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
