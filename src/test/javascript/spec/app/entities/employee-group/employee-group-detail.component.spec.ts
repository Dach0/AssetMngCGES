/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { EmployeeGroupDetailComponent } from 'app/entities/employee-group/employee-group-detail.component';
import { EmployeeGroup } from 'app/shared/model/employee-group.model';

describe('Component Tests', () => {
    describe('EmployeeGroup Management Detail Component', () => {
        let comp: EmployeeGroupDetailComponent;
        let fixture: ComponentFixture<EmployeeGroupDetailComponent>;
        const route = ({ data: of({ employeeGroup: new EmployeeGroup(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [EmployeeGroupDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EmployeeGroupDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EmployeeGroupDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.employeeGroup).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
