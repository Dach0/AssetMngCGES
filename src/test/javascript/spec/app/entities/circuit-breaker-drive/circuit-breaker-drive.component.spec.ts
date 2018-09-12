/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { CircuitBreakerDriveComponent } from 'app/entities/circuit-breaker-drive/circuit-breaker-drive.component';
import { CircuitBreakerDriveService } from 'app/entities/circuit-breaker-drive/circuit-breaker-drive.service';
import { CircuitBreakerDrive } from 'app/shared/model/circuit-breaker-drive.model';

describe('Component Tests', () => {
    describe('CircuitBreakerDrive Management Component', () => {
        let comp: CircuitBreakerDriveComponent;
        let fixture: ComponentFixture<CircuitBreakerDriveComponent>;
        let service: CircuitBreakerDriveService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [CircuitBreakerDriveComponent],
                providers: []
            })
                .overrideTemplate(CircuitBreakerDriveComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CircuitBreakerDriveComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CircuitBreakerDriveService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new CircuitBreakerDrive(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.circuitBreakerDrives[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
