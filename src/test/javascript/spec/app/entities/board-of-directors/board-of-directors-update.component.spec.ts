/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { BoardOfDirectorsUpdateComponent } from 'app/entities/board-of-directors/board-of-directors-update.component';
import { BoardOfDirectorsService } from 'app/entities/board-of-directors/board-of-directors.service';
import { BoardOfDirectors } from 'app/shared/model/board-of-directors.model';

describe('Component Tests', () => {
    describe('BoardOfDirectors Management Update Component', () => {
        let comp: BoardOfDirectorsUpdateComponent;
        let fixture: ComponentFixture<BoardOfDirectorsUpdateComponent>;
        let service: BoardOfDirectorsService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [BoardOfDirectorsUpdateComponent]
            })
                .overrideTemplate(BoardOfDirectorsUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BoardOfDirectorsUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BoardOfDirectorsService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new BoardOfDirectors(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.boardOfDirectors = entity;
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
                    const entity = new BoardOfDirectors();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.boardOfDirectors = entity;
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
