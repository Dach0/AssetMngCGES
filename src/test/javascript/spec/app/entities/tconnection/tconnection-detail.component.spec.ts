/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { TconnectionDetailComponent } from 'app/entities/tconnection/tconnection-detail.component';
import { Tconnection } from 'app/shared/model/tconnection.model';

describe('Component Tests', () => {
    describe('Tconnection Management Detail Component', () => {
        let comp: TconnectionDetailComponent;
        let fixture: ComponentFixture<TconnectionDetailComponent>;
        const route = ({ data: of({ tconnection: new Tconnection(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [TconnectionDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TconnectionDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TconnectionDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.tconnection).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
