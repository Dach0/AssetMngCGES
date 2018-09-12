/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { TitleInBoardUpdateComponent } from 'app/entities/title-in-board/title-in-board-update.component';
import { TitleInBoardService } from 'app/entities/title-in-board/title-in-board.service';
import { TitleInBoard } from 'app/shared/model/title-in-board.model';

describe('Component Tests', () => {
    describe('TitleInBoard Management Update Component', () => {
        let comp: TitleInBoardUpdateComponent;
        let fixture: ComponentFixture<TitleInBoardUpdateComponent>;
        let service: TitleInBoardService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [TitleInBoardUpdateComponent]
            })
                .overrideTemplate(TitleInBoardUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TitleInBoardUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TitleInBoardService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new TitleInBoard(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.titleInBoard = entity;
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
                    const entity = new TitleInBoard();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.titleInBoard = entity;
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
