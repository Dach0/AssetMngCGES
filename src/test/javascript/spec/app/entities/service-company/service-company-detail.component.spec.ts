/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { ServiceCompanyDetailComponent } from 'app/entities/service-company/service-company-detail.component';
import { ServiceCompany } from 'app/shared/model/service-company.model';

describe('Component Tests', () => {
    describe('ServiceCompany Management Detail Component', () => {
        let comp: ServiceCompanyDetailComponent;
        let fixture: ComponentFixture<ServiceCompanyDetailComponent>;
        const route = ({ data: of({ serviceCompany: new ServiceCompany(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [ServiceCompanyDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ServiceCompanyDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ServiceCompanyDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.serviceCompany).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
