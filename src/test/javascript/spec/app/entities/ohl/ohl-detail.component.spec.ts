/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { OhlDetailComponent } from 'app/entities/ohl/ohl-detail.component';
import { Ohl } from 'app/shared/model/ohl.model';

describe('Component Tests', () => {
    describe('Ohl Management Detail Component', () => {
        let comp: OhlDetailComponent;
        let fixture: ComponentFixture<OhlDetailComponent>;
        const route = ({ data: of({ ohl: new Ohl(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [OhlDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(OhlDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(OhlDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.ohl).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
