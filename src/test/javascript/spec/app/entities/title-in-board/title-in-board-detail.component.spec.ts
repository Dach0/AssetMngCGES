/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { TitleInBoardDetailComponent } from 'app/entities/title-in-board/title-in-board-detail.component';
import { TitleInBoard } from 'app/shared/model/title-in-board.model';

describe('Component Tests', () => {
    describe('TitleInBoard Management Detail Component', () => {
        let comp: TitleInBoardDetailComponent;
        let fixture: ComponentFixture<TitleInBoardDetailComponent>;
        const route = ({ data: of({ titleInBoard: new TitleInBoard(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [TitleInBoardDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TitleInBoardDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TitleInBoardDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.titleInBoard).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
