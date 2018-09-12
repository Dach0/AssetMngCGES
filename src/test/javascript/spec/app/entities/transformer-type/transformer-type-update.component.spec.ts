/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { TransformerTypeUpdateComponent } from 'app/entities/transformer-type/transformer-type-update.component';
import { TransformerTypeService } from 'app/entities/transformer-type/transformer-type.service';
import { TransformerType } from 'app/shared/model/transformer-type.model';

describe('Component Tests', () => {
    describe('TransformerType Management Update Component', () => {
        let comp: TransformerTypeUpdateComponent;
        let fixture: ComponentFixture<TransformerTypeUpdateComponent>;
        let service: TransformerTypeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [TransformerTypeUpdateComponent]
            })
                .overrideTemplate(TransformerTypeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TransformerTypeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TransformerTypeService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new TransformerType(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.transformerType = entity;
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
                    const entity = new TransformerType();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.transformerType = entity;
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
