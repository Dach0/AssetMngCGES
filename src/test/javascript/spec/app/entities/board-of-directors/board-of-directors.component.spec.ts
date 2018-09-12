/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { BoardOfDirectorsComponent } from 'app/entities/board-of-directors/board-of-directors.component';
import { BoardOfDirectorsService } from 'app/entities/board-of-directors/board-of-directors.service';
import { BoardOfDirectors } from 'app/shared/model/board-of-directors.model';

describe('Component Tests', () => {
    describe('BoardOfDirectors Management Component', () => {
        let comp: BoardOfDirectorsComponent;
        let fixture: ComponentFixture<BoardOfDirectorsComponent>;
        let service: BoardOfDirectorsService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [BoardOfDirectorsComponent],
                providers: []
            })
                .overrideTemplate(BoardOfDirectorsComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BoardOfDirectorsComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BoardOfDirectorsService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new BoardOfDirectors(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.boardOfDirectors[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
