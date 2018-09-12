/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { ServiceCompanyUpdateComponent } from 'app/entities/service-company/service-company-update.component';
import { ServiceCompanyService } from 'app/entities/service-company/service-company.service';
import { ServiceCompany } from 'app/shared/model/service-company.model';

describe('Component Tests', () => {
    describe('ServiceCompany Management Update Component', () => {
        let comp: ServiceCompanyUpdateComponent;
        let fixture: ComponentFixture<ServiceCompanyUpdateComponent>;
        let service: ServiceCompanyService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [ServiceCompanyUpdateComponent]
            })
                .overrideTemplate(ServiceCompanyUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ServiceCompanyUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ServiceCompanyService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ServiceCompany(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.serviceCompany = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ServiceCompany();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.serviceCompany = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
