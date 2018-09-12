/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { ServiceTypeDetailComponent } from 'app/entities/service-type/service-type-detail.component';
import { ServiceType } from 'app/shared/model/service-type.model';

describe('Component Tests', () => {
    describe('ServiceType Management Detail Component', () => {
        let comp: ServiceTypeDetailComponent;
        let fixture: ComponentFixture<ServiceTypeDetailComponent>;
        const route = ({ data: of({ serviceType: new ServiceType(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [ServiceTypeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ServiceTypeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ServiceTypeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.serviceType).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
