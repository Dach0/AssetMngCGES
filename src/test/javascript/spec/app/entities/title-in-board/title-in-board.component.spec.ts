/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { TitleInBoardComponent } from 'app/entities/title-in-board/title-in-board.component';
import { TitleInBoardService } from 'app/entities/title-in-board/title-in-board.service';
import { TitleInBoard } from 'app/shared/model/title-in-board.model';

describe('Component Tests', () => {
    describe('TitleInBoard Management Component', () => {
        let comp: TitleInBoardComponent;
        let fixture: ComponentFixture<TitleInBoardComponent>;
        let service: TitleInBoardService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [TitleInBoardComponent],
                providers: []
            })
                .overrideTemplate(TitleInBoardComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TitleInBoardComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TitleInBoardService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new TitleInBoard(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.titleInBoards[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
