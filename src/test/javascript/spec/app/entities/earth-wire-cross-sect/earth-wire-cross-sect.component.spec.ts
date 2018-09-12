/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { EarthWireCrossSectComponent } from 'app/entities/earth-wire-cross-sect/earth-wire-cross-sect.component';
import { EarthWireCrossSectService } from 'app/entities/earth-wire-cross-sect/earth-wire-cross-sect.service';
import { EarthWireCrossSect } from 'app/shared/model/earth-wire-cross-sect.model';

describe('Component Tests', () => {
    describe('EarthWireCrossSect Management Component', () => {
        let comp: EarthWireCrossSectComponent;
        let fixture: ComponentFixture<EarthWireCrossSectComponent>;
        let service: EarthWireCrossSectService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [EarthWireCrossSectComponent],
                providers: []
            })
                .overrideTemplate(EarthWireCrossSectComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EarthWireCrossSectComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EarthWireCrossSectService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new EarthWireCrossSect(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.earthWireCrossSects[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
