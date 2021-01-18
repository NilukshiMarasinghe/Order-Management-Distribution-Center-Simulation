import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PackerDashboarComponent } from './packer-dashboar.component';

describe('PackerDashboarComponent', () => {
  let component: PackerDashboarComponent;
  let fixture: ComponentFixture<PackerDashboarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PackerDashboarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PackerDashboarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
