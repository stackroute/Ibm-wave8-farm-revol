import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NavbarFarmerComponent } from './navbar-farmer.component';

describe('NavbarFarmerComponent', () => {
  let component: NavbarFarmerComponent;
  let fixture: ComponentFixture<NavbarFarmerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NavbarFarmerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NavbarFarmerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
