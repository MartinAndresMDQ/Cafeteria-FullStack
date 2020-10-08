import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

import { Client } from '@model';
import { ClientService } from '@services';

@Component({
  selector: 'login',
  templateUrl: './login.component.html'
})
export class LoginComponent {

  public username = '';
  public password = '';
  public verPass = false;
  constructor(
    @Inject(ClientService) private _clientService: ClientService,
    public dialogRef: MatDialogRef<LoginComponent>) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

  login() {
    if (!this.verPass)
      this._clientService.loginClient(this.username).subscribe(
        data => {
          this.dialogRef.close(data);
        },
        error => {
          window.alert(error);
          console.error(<any>error)
        });
    else
      this._clientService.login(this.username, this.password).subscribe(
        data => {
          this.dialogRef.close(data);
        },
        error => {
          window.alert(error);
          console.error(<any>error)
        });
  }

}