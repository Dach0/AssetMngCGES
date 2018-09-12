/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { DisconnectorLineExitDetailComponent } from 'app/entities/disconnector-line-exit/disconnector-line-exit-detail.component';
import { DisconnectorLineExit } from 'app/shared/model/disconnector-line-exit.model';

describe('Component Tests', () => {
    describe('DisconnectorLineExit Management Detail Component', () => {
        let comp: DisconnectorLineExitDetailComponent;
        let fixture: ComponentFixture<DisconnectorLineExitDetailComponent>;
        const route = ({ data: of({ disconnectorLineExit: new DisconnectorLineExit(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [DisconnectorLineExitDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DisconnectorLineExitDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DisconnectorLineExitDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.disconnectorLineExit).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
