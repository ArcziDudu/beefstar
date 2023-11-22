import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FobiddenComponent } from './fobidden.component';

describe('FobiddenComponent', () => {
  let component: FobiddenComponent;
  let fixture: ComponentFixture<FobiddenComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FobiddenComponent]
    });
    fixture = TestBed.createComponent(FobiddenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
