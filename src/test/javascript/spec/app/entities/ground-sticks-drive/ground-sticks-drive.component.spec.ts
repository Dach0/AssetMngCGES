/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { GroundSticksDriveComponent } from 'app/entities/ground-sticks-drive/ground-sticks-drive.component';
import { GroundSticksDriveService } from 'app/entities/ground-sticks-drive/ground-sticks-drive.service';
import { GroundSticksDrive } from 'app/shared/model/ground-sticks-drive.model';

describe('Component Tests', () => {
    describe('GroundSticksDrive Management Component', () => {
        let comp: GroundSticksDriveComponent;
        let fixture: ComponentFixture<GroundSticksDriveComponent>;
        let service: GroundSticksDriveService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [GroundSticksDriveComponent],
                providers: []
            })
                .overrideTemplate(GroundSticksDriveComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(GroundSticksDriveComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GroundSticksDriveService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new GroundSticksDrive(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.groundSticksDrives[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
