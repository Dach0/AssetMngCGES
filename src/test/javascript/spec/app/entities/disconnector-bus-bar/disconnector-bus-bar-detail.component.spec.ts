/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { DisconnectorBusBarDetailComponent } from 'app/entities/disconnector-bus-bar/disconnector-bus-bar-detail.component';
import { DisconnectorBusBar } from 'app/shared/model/disconnector-bus-bar.model';

describe('Component Tests', () => {
    describe('DisconnectorBusBar Management Detail Component', () => {
        let comp: DisconnectorBusBarDetailComponent;
        let fixture: ComponentFixture<DisconnectorBusBarDetailComponent>;
        const route = ({ data: of({ disconnectorBusBar: new DisconnectorBusBar(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [DisconnectorBusBarDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DisconnectorBusBarDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DisconnectorBusBarDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.disconnectorBusBar).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
