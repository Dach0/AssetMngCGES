/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { CircuitBreakerDriveDetailComponent } from 'app/entities/circuit-breaker-drive/circuit-breaker-drive-detail.component';
import { CircuitBreakerDrive } from 'app/shared/model/circuit-breaker-drive.model';

describe('Component Tests', () => {
    describe('CircuitBreakerDrive Management Detail Component', () => {
        let comp: CircuitBreakerDriveDetailComponent;
        let fixture: ComponentFixture<CircuitBreakerDriveDetailComponent>;
        const route = ({ data: of({ circuitBreakerDrive: new CircuitBreakerDrive(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [CircuitBreakerDriveDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CircuitBreakerDriveDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CircuitBreakerDriveDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.circuitBreakerDrive).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
