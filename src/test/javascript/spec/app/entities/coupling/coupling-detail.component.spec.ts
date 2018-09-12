/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { CouplingDetailComponent } from 'app/entities/coupling/coupling-detail.component';
import { Coupling } from 'app/shared/model/coupling.model';

describe('Component Tests', () => {
    describe('Coupling Management Detail Component', () => {
        let comp: CouplingDetailComponent;
        let fixture: ComponentFixture<CouplingDetailComponent>;
        const route = ({ data: of({ coupling: new Coupling(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [CouplingDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CouplingDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CouplingDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.coupling).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
