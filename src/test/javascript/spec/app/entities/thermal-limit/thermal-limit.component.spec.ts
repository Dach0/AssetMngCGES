/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { ThermalLimitComponent } from 'app/entities/thermal-limit/thermal-limit.component';
import { ThermalLimitService } from 'app/entities/thermal-limit/thermal-limit.service';
import { ThermalLimit } from 'app/shared/model/thermal-limit.model';

describe('Component Tests', () => {
    describe('ThermalLimit Management Component', () => {
        let comp: ThermalLimitComponent;
        let fixture: ComponentFixture<ThermalLimitComponent>;
        let service: ThermalLimitService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [ThermalLimitComponent],
                providers: []
            })
                .overrideTemplate(ThermalLimitComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ThermalLimitComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ThermalLimitService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ThermalLimit(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.thermalLimits[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
