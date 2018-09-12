/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { BoardMemberComponent } from 'app/entities/board-member/board-member.component';
import { BoardMemberService } from 'app/entities/board-member/board-member.service';
import { BoardMember } from 'app/shared/model/board-member.model';

describe('Component Tests', () => {
    describe('BoardMember Management Component', () => {
        let comp: BoardMemberComponent;
        let fixture: ComponentFixture<BoardMemberComponent>;
        let service: BoardMemberService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [BoardMemberComponent],
                providers: []
            })
                .overrideTemplate(BoardMemberComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BoardMemberComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BoardMemberService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new BoardMember(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.boardMembers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
