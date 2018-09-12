/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { DisconnectorTypeDetailComponent } from 'app/entities/disconnector-type/disconnector-type-detail.component';
import { DisconnectorType } from 'app/shared/model/disconnector-type.model';

describe('Component Tests', () => {
    describe('DisconnectorType Management Detail Component', () => {
        let comp: DisconnectorTypeDetailComponent;
        let fixture: ComponentFixture<DisconnectorTypeDetailComponent>;
        const route = ({ data: of({ disconnectorType: new DisconnectorType(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [DisconnectorTypeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DisconnectorTypeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DisconnectorTypeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.disconnectorType).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
