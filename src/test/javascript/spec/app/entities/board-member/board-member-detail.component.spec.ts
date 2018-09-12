/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { BoardMemberDetailComponent } from 'app/entities/board-member/board-member-detail.component';
import { BoardMember } from 'app/shared/model/board-member.model';

describe('Component Tests', () => {
    describe('BoardMember Management Detail Component', () => {
        let comp: BoardMemberDetailComponent;
        let fixture: ComponentFixture<BoardMemberDetailComponent>;
        const route = ({ data: of({ boardMember: new BoardMember(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [BoardMemberDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(BoardMemberDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BoardMemberDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.boardMember).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
