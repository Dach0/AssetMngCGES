/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { TransformerComponent } from 'app/entities/transformer/transformer.component';
import { TransformerService } from 'app/entities/transformer/transformer.service';
import { Transformer } from 'app/shared/model/transformer.model';

describe('Component Tests', () => {
    describe('Transformer Management Component', () => {
        let comp: TransformerComponent;
        let fixture: ComponentFixture<TransformerComponent>;
        let service: TransformerService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [TransformerComponent],
                providers: []
            })
                .overrideTemplate(TransformerComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TransformerComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TransformerService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Transformer(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.transformers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
