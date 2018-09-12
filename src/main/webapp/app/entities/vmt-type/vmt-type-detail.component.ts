import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVmtType } from 'app/shared/model/vmt-type.model';

@Component({
    selector: 'jhi-vmt-type-detail',
    templateUrl: './vmt-type-detail.component.html'
})
export class VmtTypeDetailComponent implements OnInit {
    vmtType: IVmtType;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ vmtType }) => {
            this.vmtType = vmtType;
        });
    }

    previousState() {
        window.history.back();
    }
}
