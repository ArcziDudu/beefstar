import { Component } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';

@Component({
  selector: 'app-order-confirmation',
  templateUrl: './order-confirmation.component.html',
  styleUrls: ['./order-confirmation.component.css']
})
export class OrderConfirmationComponent {
  constructor(public dialogRef: MatDialogRef<OrderConfirmationComponent>,private router: Router) {}

  closeDialog(): void {
    this.dialogRef.close();
    this.router.navigate(['/home'])
  }
}
