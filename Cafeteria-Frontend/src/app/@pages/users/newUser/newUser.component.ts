import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

import { Client, Manager, Operator, Supervisor } from '@model';
import { ClientService } from '@services';

@Component({
  selector: 'newUser',
  templateUrl: './newUser.component.html'
})
export class NewUserComponent implements OnInit {

  public selected = 'Client';
  public username = '';
  public password = '';

  public available = false;

  public online = false;

  constructor(@Inject(ClientService) private _userService: ClientService,
    public dialogRef: MatDialogRef<NewUserComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { client, isNew: Boolean }) { }

  ngOnInit(): void {
    if (!this.data.isNew) {
      // if (this.data.client.DTYPE == "Client")
      this.selected = this.data.client.DTYPE
      // // else if (this.data.client.DTYPE == "Operator")
      //   this.selected = 'option1'
      // else if (this.data.client.DTYPE == "Supervisor")
      //   this.selected = 'option2'
      // else if (this.data.client.DTYPE == "Manager")
      //   this.selected = 'option3'
      this.username = this.data.client.name;
      this.password = this.data.client.password;
      this.available = this.data.client.available;
      this.online = this.data.client.online;
    }
  }
  onNoClick(): void {
    this.dialogRef.close();
  }

  continuar() {

    if (this.selected == 'Client') {
      let client: Client = new Client(this.data.client.id, this.username, false)

      this._userService.save(client).subscribe(
        data => {

          this.dialogRef.close(data);
        },
        error => {
          window.alert(error);
          console.error(<any>error)
        });
    }
    else if (this.selected == 'Operator') {

      let client: Operator = new Operator(this.data.client.id, this.username, this.online, this.available, this.password)

      this._userService.saveOperator(client).subscribe(
        data => {
          this.dialogRef.close(data);
        },
        error => {
          window.alert(error);
          console.error(<any>error)
        });
    }
    else if (this.selected == 'Supervisor') {
      let client: Supervisor = new Supervisor(this.data.client.id, this.username, this.online, this.available, this.password)
      this._userService.saveSupervisor(client).subscribe(
        data => {
          this.dialogRef.close(data);
        },
        error => {
          window.alert(error);
          console.error(<any>error)
        });
    }
    else if (this.selected == 'Manager') {
      let client: Manager = new Manager(this.data.client.id, this.username, this.online, this.available, this.password)
      this._userService.saveManager(client).subscribe(
        data => {
          this.dialogRef.close(data);
        },
        error => {
          window.alert(error);
          console.error(<any>error)
        });
    }
  }
  save() {

    if (this.data.client.id != 0 && this.data.client.DTYPE != this.selected) {

      this._userService.delete(this.data.client.id).subscribe(
        data => {
          this.data.client.id = 0;
          // this.TraerTodo();
          this.continuar();
        },
        error => {
          window.alert(error);
          console.error(<any>error)
        });
    }
    else
      this.continuar();


    // }

  }

}