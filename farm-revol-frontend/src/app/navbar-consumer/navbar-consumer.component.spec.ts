import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NavbarConsumerComponent } from './navbar-consumer.component';

describe('NavbarConsumerComponent', () => {
  let component: NavbarConsumerComponent;
  let fixture: ComponentFixture<NavbarConsumerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NavbarConsumerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NavbarConsumerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
