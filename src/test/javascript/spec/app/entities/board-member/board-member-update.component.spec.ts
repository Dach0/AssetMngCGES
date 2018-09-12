/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { BoardMemberUpdateComponent } from 'app/entities/board-member/board-member-update.component';
import { BoardMemberService } from 'app/entities/board-member/board-member.service';
import { BoardMember } from 'app/shared/model/board-member.model';

describe('Component Tests', () => {
    describe('BoardMember Management Update Component', () => {
        let comp: BoardMemberUpdateComponent;
        let fixture: ComponentFixture<BoardMemberUpdateComponent>;
        let service: BoardMemberService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [BoardMemberUpdateComponent]
            })
                .overrideTemplate(BoardMemberUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BoardMemberUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BoardMemberService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new BoardMember(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.boardMember = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new BoardMember();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.boardMember = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
