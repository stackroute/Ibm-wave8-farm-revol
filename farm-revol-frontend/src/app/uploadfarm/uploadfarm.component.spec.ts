import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadfarmComponent } from './uploadfarm.component';

describe('UploadfarmComponent', () => {
  let component: UploadfarmComponent;
  let fixture: ComponentFixture<UploadfarmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UploadfarmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UploadfarmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
