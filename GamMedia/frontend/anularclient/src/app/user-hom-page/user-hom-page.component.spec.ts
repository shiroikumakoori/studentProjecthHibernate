import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserHomPageComponent } from './user-hom-page.component';

describe('UserHomPageComponent', () => {
  let component: UserHomPageComponent;
  let fixture: ComponentFixture<UserHomPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserHomPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserHomPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
