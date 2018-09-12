/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { EarthWireCrossSectDetailComponent } from 'app/entities/earth-wire-cross-sect/earth-wire-cross-sect-detail.component';
import { EarthWireCrossSect } from 'app/shared/model/earth-wire-cross-sect.model';

describe('Component Tests', () => {
    describe('EarthWireCrossSect Management Detail Component', () => {
        let comp: EarthWireCrossSectDetailComponent;
        let fixture: ComponentFixture<EarthWireCrossSectDetailComponent>;
        const route = ({ data: of({ earthWireCrossSect: new EarthWireCrossSect(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [EarthWireCrossSectDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EarthWireCrossSectDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EarthWireCrossSectDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.earthWireCrossSect).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
