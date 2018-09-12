/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { ServiceCompanyComponent } from 'app/entities/service-company/service-company.component';
import { ServiceCompanyService } from 'app/entities/service-company/service-company.service';
import { ServiceCompany } from 'app/shared/model/service-company.model';

describe('Component Tests', () => {
    describe('ServiceCompany Management Component', () => {
        let comp: ServiceCompanyComponent;
        let fixture: ComponentFixture<ServiceCompanyComponent>;
        let service: ServiceCompanyService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [ServiceCompanyComponent],
                providers: []
            })
                .overrideTemplate(ServiceCompanyComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ServiceCompanyComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ServiceCompanyService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ServiceCompany(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.serviceCompanies[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
