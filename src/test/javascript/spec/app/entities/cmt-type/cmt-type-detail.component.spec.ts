/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { CmtTypeDetailComponent } from 'app/entities/cmt-type/cmt-type-detail.component';
import { CmtType } from 'app/shared/model/cmt-type.model';

describe('Component Tests', () => {
    describe('CmtType Management Detail Component', () => {
        let comp: CmtTypeDetailComponent;
        let fixture: ComponentFixture<CmtTypeDetailComponent>;
        const route = ({ data: of({ cmtType: new CmtType(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [CmtTypeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CmtTypeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CmtTypeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.cmtType).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
