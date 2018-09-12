/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { TransformerTypeComponent } from 'app/entities/transformer-type/transformer-type.component';
import { TransformerTypeService } from 'app/entities/transformer-type/transformer-type.service';
import { TransformerType } from 'app/shared/model/transformer-type.model';

describe('Component Tests', () => {
    describe('TransformerType Management Component', () => {
        let comp: TransformerTypeComponent;
        let fixture: ComponentFixture<TransformerTypeComponent>;
        let service: TransformerTypeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [TransformerTypeComponent],
                providers: []
            })
                .overrideTemplate(TransformerTypeComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TransformerTypeComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TransformerTypeService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new TransformerType(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.transformerTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
