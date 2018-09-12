/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { VmtTypeDetailComponent } from 'app/entities/vmt-type/vmt-type-detail.component';
import { VmtType } from 'app/shared/model/vmt-type.model';

describe('Component Tests', () => {
    describe('VmtType Management Detail Component', () => {
        let comp: VmtTypeDetailComponent;
        let fixture: ComponentFixture<VmtTypeDetailComponent>;
        const route = ({ data: of({ vmtType: new VmtType(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [VmtTypeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(VmtTypeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(VmtTypeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.vmtType).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
