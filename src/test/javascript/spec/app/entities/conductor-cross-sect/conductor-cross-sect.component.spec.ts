/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { ConductorCrossSectComponent } from 'app/entities/conductor-cross-sect/conductor-cross-sect.component';
import { ConductorCrossSectService } from 'app/entities/conductor-cross-sect/conductor-cross-sect.service';
import { ConductorCrossSect } from 'app/shared/model/conductor-cross-sect.model';

describe('Component Tests', () => {
    describe('ConductorCrossSect Management Component', () => {
        let comp: ConductorCrossSectComponent;
        let fixture: ComponentFixture<ConductorCrossSectComponent>;
        let service: ConductorCrossSectService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [ConductorCrossSectComponent],
                providers: []
            })
                .overrideTemplate(ConductorCrossSectComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ConductorCrossSectComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConductorCrossSectService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ConductorCrossSect(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.conductorCrossSects[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
