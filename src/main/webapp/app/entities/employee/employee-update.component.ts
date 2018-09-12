import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IEmployee } from 'app/shared/model/employee.model';
import { EmployeeService } from './employee.service';
import { IPhonePrivilage } from 'app/shared/model/phone-privilage.model';
import { PhonePrivilageService } from 'app/entities/phone-privilage';
import { IEmployeeGroup } from 'app/shared/model/employee-group.model';
import { EmployeeGroupService } from 'app/entities/employee-group';
import { ISector } from 'app/shared/model/sector.model';
import { SectorService } from 'app/entities/sector';
import { IDepartman } from 'app/shared/model/departman.model';
import { DepartmanService } from 'app/entities/departman';
import { IJob } from 'app/shared/model/job.model';
import { JobService } from 'app/entities/job';
import { IJobStatus } from 'app/shared/model/job-status.model';
import { JobStatusService } from 'app/entities/job-status';
import { IQualification } from 'app/shared/model/qualification.model';
import { QualificationService } from 'app/entities/qualification';
import { IContract } from 'app/shared/model/contract.model';
import { ContractService } from 'app/entities/contract';

@Component({
    selector: 'jhi-employee-update',
    templateUrl: './employee-update.component.html'
})
export class EmployeeUpdateComponent implements OnInit {
    private _employee: IEmployee;
    isSaving: boolean;

    phoneprivilages: IPhonePrivilage[];

    employeegroups: IEmployeeGroup[];

    sectors: ISector[];

    departmen: IDepartman[];

    jobs: IJob[];

    jobstatuses: IJobStatus[];

    qualifications: IQualification[];

    contracts: IContract[];
    startDateDp: any;
    endDateDp: any;

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private employeeService: EmployeeService,
        private phonePrivilageService: PhonePrivilageService,
        private employeeGroupService: EmployeeGroupService,
        private sectorService: SectorService,
        private departmanService: DepartmanService,
        private jobService: JobService,
        private jobStatusService: JobStatusService,
        private qualificationService: QualificationService,
        private contractService: ContractService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ employee }) => {
            this.employee = employee;
        });
        this.phonePrivilageService.query().subscribe(
            (res: HttpResponse<IPhonePrivilage[]>) => {
                this.phoneprivilages = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.employeeGroupService.query().subscribe(
            (res: HttpResponse<IEmployeeGroup[]>) => {
                this.employeegroups = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.sectorService.query().subscribe(
            (res: HttpResponse<ISector[]>) => {
                this.sectors = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.departmanService.query().subscribe(
            (res: HttpResponse<IDepartman[]>) => {
                this.departmen = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.jobService.query().subscribe(
            (res: HttpResponse<IJob[]>) => {
                this.jobs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.jobStatusService.query().subscribe(
            (res: HttpResponse<IJobStatus[]>) => {
                this.jobstatuses = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.qualificationService.query().subscribe(
            (res: HttpResponse<IQualification[]>) => {
                this.qualifications = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.contractService.query().subscribe(
            (res: HttpResponse<IContract[]>) => {
                this.contracts = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.employee.id !== undefined) {
            this.subscribeToSaveResponse(this.employeeService.update(this.employee));
        } else {
            this.subscribeToSaveResponse(this.employeeService.create(this.employee));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEmployee>>) {
        result.subscribe((res: HttpResponse<IEmployee>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackPhonePrivilageById(index: number, item: IPhonePrivilage) {
        return item.id;
    }

    trackEmployeeGroupById(index: number, item: IEmployeeGroup) {
        return item.id;
    }

    trackSectorById(index: number, item: ISector) {
        return item.id;
    }

    trackDepartmanById(index: number, item: IDepartman) {
        return item.id;
    }

    trackJobById(index: number, item: IJob) {
        return item.id;
    }

    trackJobStatusById(index: number, item: IJobStatus) {
        return item.id;
    }

    trackQualificationById(index: number, item: IQualification) {
        return item.id;
    }

    trackContractById(index: number, item: IContract) {
        return item.id;
    }
    get employee() {
        return this._employee;
    }

    set employee(employee: IEmployee) {
        this._employee = employee;
    }
}
