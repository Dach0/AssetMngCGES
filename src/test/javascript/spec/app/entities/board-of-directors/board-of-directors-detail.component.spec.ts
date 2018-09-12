/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { BoardOfDirectorsDetailComponent } from 'app/entities/board-of-directors/board-of-directors-detail.component';
import { BoardOfDirectors } from 'app/shared/model/board-of-directors.model';

describe('Component Tests', () => {
    describe('BoardOfDirectors Management Detail Component', () => {
        let comp: BoardOfDirectorsDetailComponent;
        let fixture: ComponentFixture<BoardOfDirectorsDetailComponent>;
        const route = ({ data: of({ boardOfDirectors: new BoardOfDirectors(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [BoardOfDirectorsDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(BoardOfDirectorsDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BoardOfDirectorsDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.boardOfDirectors).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
