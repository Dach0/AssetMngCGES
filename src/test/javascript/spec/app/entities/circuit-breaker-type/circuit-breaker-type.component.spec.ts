/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { CircuitBreakerTypeComponent } from 'app/entities/circuit-breaker-type/circuit-breaker-type.component';
import { CircuitBreakerTypeService } from 'app/entities/circuit-breaker-type/circuit-breaker-type.service';
import { CircuitBreakerType } from 'app/shared/model/circuit-breaker-type.model';

describe('Component Tests', () => {
    describe('CircuitBreakerType Management Component', () => {
        let comp: CircuitBreakerTypeComponent;
        let fixture: ComponentFixture<CircuitBreakerTypeComponent>;
        let service: CircuitBreakerTypeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [CircuitBreakerTypeComponent],
                providers: []
            })
                .overrideTemplate(CircuitBreakerTypeComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CircuitBreakerTypeComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CircuitBreakerTypeService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new CircuitBreakerType(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.circuitBreakerTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
