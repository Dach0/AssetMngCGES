/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { VmtTypeComponent } from 'app/entities/vmt-type/vmt-type.component';
import { VmtTypeService } from 'app/entities/vmt-type/vmt-type.service';
import { VmtType } from 'app/shared/model/vmt-type.model';

describe('Component Tests', () => {
    describe('VmtType Management Component', () => {
        let comp: VmtTypeComponent;
        let fixture: ComponentFixture<VmtTypeComponent>;
        let service: VmtTypeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [VmtTypeComponent],
                providers: []
            })
                .overrideTemplate(VmtTypeComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(VmtTypeComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VmtTypeService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new VmtType(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.vmtTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
