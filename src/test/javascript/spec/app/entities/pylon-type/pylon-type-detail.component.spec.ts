/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { PylonTypeDetailComponent } from 'app/entities/pylon-type/pylon-type-detail.component';
import { PylonType } from 'app/shared/model/pylon-type.model';

describe('Component Tests', () => {
    describe('PylonType Management Detail Component', () => {
        let comp: PylonTypeDetailComponent;
        let fixture: ComponentFixture<PylonTypeDetailComponent>;
        const route = ({ data: of({ pylonType: new PylonType(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [PylonTypeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PylonTypeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PylonTypeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.pylonType).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
