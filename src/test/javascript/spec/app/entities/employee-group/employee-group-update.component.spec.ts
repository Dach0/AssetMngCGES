/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { EmployeeGroupUpdateComponent } from 'app/entities/employee-group/employee-group-update.component';
import { EmployeeGroupService } from 'app/entities/employee-group/employee-group.service';
import { EmployeeGroup } from 'app/shared/model/employee-group.model';

describe('Component Tests', () => {
    describe('EmployeeGroup Management Update Component', () => {
        let comp: EmployeeGroupUpdateComponent;
        let fixture: ComponentFixture<EmployeeGroupUpdateComponent>;
        let service: EmployeeGroupService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [EmployeeGroupUpdateComponent]
            })
                .overrideTemplate(EmployeeGroupUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EmployeeGroupUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EmployeeGroupService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EmployeeGroup(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.employeeGroup = entity;
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
                    const entity = new EmployeeGroup();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.employeeGroup = entity;
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
