/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { TransformerUpdateComponent } from 'app/entities/transformer/transformer-update.component';
import { TransformerService } from 'app/entities/transformer/transformer.service';
import { Transformer } from 'app/shared/model/transformer.model';

describe('Component Tests', () => {
    describe('Transformer Management Update Component', () => {
        let comp: TransformerUpdateComponent;
        let fixture: ComponentFixture<TransformerUpdateComponent>;
        let service: TransformerService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [TransformerUpdateComponent]
            })
                .overrideTemplate(TransformerUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TransformerUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TransformerService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Transformer(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.transformer = entity;
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
                    const entity = new Transformer();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.transformer = entity;
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
