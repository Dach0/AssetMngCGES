/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { TransformerTypeDetailComponent } from 'app/entities/transformer-type/transformer-type-detail.component';
import { TransformerType } from 'app/shared/model/transformer-type.model';

describe('Component Tests', () => {
    describe('TransformerType Management Detail Component', () => {
        let comp: TransformerTypeDetailComponent;
        let fixture: ComponentFixture<TransformerTypeDetailComponent>;
        const route = ({ data: of({ transformerType: new TransformerType(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [TransformerTypeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TransformerTypeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TransformerTypeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.transformerType).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
