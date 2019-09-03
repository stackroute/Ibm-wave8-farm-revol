import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsumerLandingPageComponent } from './consumer-landing-page.component';

describe('ConsumerLandingPageComponent', () => {
  let component: ConsumerLandingPageComponent;
  let fixture: ComponentFixture<ConsumerLandingPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConsumerLandingPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsumerLandingPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
