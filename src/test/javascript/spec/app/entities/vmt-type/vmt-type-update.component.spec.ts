/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { VmtTypeUpdateComponent } from 'app/entities/vmt-type/vmt-type-update.component';
import { VmtTypeService } from 'app/entities/vmt-type/vmt-type.service';
import { VmtType } from 'app/shared/model/vmt-type.model';

describe('Component Tests', () => {
    describe('VmtType Management Update Component', () => {
        let comp: VmtTypeUpdateComponent;
        let fixture: ComponentFixture<VmtTypeUpdateComponent>;
        let service: VmtTypeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [VmtTypeUpdateComponent]
            })
                .overrideTemplate(VmtTypeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(VmtTypeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VmtTypeService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new VmtType(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.vmtType = entity;
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
                    const entity = new VmtType();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.vmtType = entity;
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
