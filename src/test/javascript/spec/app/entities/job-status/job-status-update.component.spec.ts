/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { JobStatusUpdateComponent } from 'app/entities/job-status/job-status-update.component';
import { JobStatusService } from 'app/entities/job-status/job-status.service';
import { JobStatus } from 'app/shared/model/job-status.model';

describe('Component Tests', () => {
    describe('JobStatus Management Update Component', () => {
        let comp: JobStatusUpdateComponent;
        let fixture: ComponentFixture<JobStatusUpdateComponent>;
        let service: JobStatusService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [JobStatusUpdateComponent]
            })
                .overrideTemplate(JobStatusUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(JobStatusUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(JobStatusService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new JobStatus(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.jobStatus = entity;
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
                    const entity = new JobStatus();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.jobStatus = entity;
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
