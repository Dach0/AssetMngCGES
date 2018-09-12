/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { TransmissionRatioComponent } from 'app/entities/transmission-ratio/transmission-ratio.component';
import { TransmissionRatioService } from 'app/entities/transmission-ratio/transmission-ratio.service';
import { TransmissionRatio } from 'app/shared/model/transmission-ratio.model';

describe('Component Tests', () => {
    describe('TransmissionRatio Management Component', () => {
        let comp: TransmissionRatioComponent;
        let fixture: ComponentFixture<TransmissionRatioComponent>;
        let service: TransmissionRatioService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [TransmissionRatioComponent],
                providers: []
            })
                .overrideTemplate(TransmissionRatioComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TransmissionRatioComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TransmissionRatioService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new TransmissionRatio(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.transmissionRatios[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
