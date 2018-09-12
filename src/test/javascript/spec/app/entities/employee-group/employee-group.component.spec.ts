/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { EmployeeGroupComponent } from 'app/entities/employee-group/employee-group.component';
import { EmployeeGroupService } from 'app/entities/employee-group/employee-group.service';
import { EmployeeGroup } from 'app/shared/model/employee-group.model';

describe('Component Tests', () => {
    describe('EmployeeGroup Management Component', () => {
        let comp: EmployeeGroupComponent;
        let fixture: ComponentFixture<EmployeeGroupComponent>;
        let service: EmployeeGroupService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [EmployeeGroupComponent],
                providers: []
            })
                .overrideTemplate(EmployeeGroupComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EmployeeGroupComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EmployeeGroupService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new EmployeeGroup(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.employeeGroups[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
