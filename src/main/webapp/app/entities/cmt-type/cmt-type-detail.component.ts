import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICmtType } from 'app/shared/model/cmt-type.model';

@Component({
    selector: 'jhi-cmt-type-detail',
    templateUrl: './cmt-type-detail.component.html'
})
export class CmtTypeDetailComponent implements OnInit {
    cmtType: ICmtType;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ cmtType }) => {
            this.cmtType = cmtType;
        });
    }

    previousState() {
        window.history.back();
    }
}
