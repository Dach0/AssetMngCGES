/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { CmtTypeComponent } from 'app/entities/cmt-type/cmt-type.component';
import { CmtTypeService } from 'app/entities/cmt-type/cmt-type.service';
import { CmtType } from 'app/shared/model/cmt-type.model';

describe('Component Tests', () => {
    describe('CmtType Management Component', () => {
        let comp: CmtTypeComponent;
        let fixture: ComponentFixture<CmtTypeComponent>;
        let service: CmtTypeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [CmtTypeComponent],
                providers: []
            })
                .overrideTemplate(CmtTypeComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CmtTypeComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CmtTypeService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new CmtType(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.cmtTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
