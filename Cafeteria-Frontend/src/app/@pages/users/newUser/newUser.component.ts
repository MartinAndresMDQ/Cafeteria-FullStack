import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

import { Client, Manager, Operator, Supervisor } from '@model';
import { ClientService } from '@services';

@Component({
  selector: 'newUser',
  templateUrl: './newUser.component.html'
})
export class NewUserComponent {

  public selected = 'option0';
  public username = '';
  public password = '';

  constructor(@Inject(ClientService) private _userService: ClientService,
    public dialogRef: MatDialogRef<NewUserComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { client, isNew: Boolean }) { }

  ngOnInit(): void {
    if (!this.data.isNew) {
      if(this.data.client.DTYPE=="Client")
      this.selected = 'option0'
      else if(this.data.client.DTYPE=="Operator")
      this.selected = 'option1'
      else if(this.data.client.DTYPE=="Supervisor")
      this.selected = 'option2'
      else if(this.data.client.DTYPE=="Manager")
      this.selected = 'option3'
      this.username = this.data.client.name;
      this.password = this.data.client.password;
     }
  }
  onNoClick(): void {
    this.dialogRef.close();
  }

  save() {
    console.log(this.password)
    // if(!this.data.isUser){
    if (this.selected == 'option0') {
      let client: Client = new Client(0, this.username, false)

      this._userService.save(this.data.client).subscribe(
        data => {

          console.log(data)
          this.dialogRef.close(data);
        },
        error => {
          window.alert(error);
          console.error(<any>error)
        });
    }
    else if (this.selected == 'option1') {
      let client: Operator = new Operator(0, this.username, false, this.password)

      console.log(client)
      this._userService.saveOperator(client).subscribe(
        data => {
          console.log(data)
          this.dialogRef.close(data);
        },
        error => {
          window.alert(error);
          console.error(<any>error)
        });
    }
    else if (this.selected == 'option2') {
      let client: Supervisor = new Supervisor(0, this.username, false, this.password)
      this._userService.saveSupervisor(client).subscribe(
        data => {
          console.log(data)
          this.dialogRef.close(data);
        },
        error => {
          window.alert(error);
          console.error(<any>error)
        });
    }
    else if (this.selected == 'option3') {
      let client: Manager = new Manager(0, this.username, false, this.password)
      this._userService.saveManager(client).subscribe(
        data => {
          this.dialogRef.close(data);
        },
        error => {
          window.alert(error);
          console.error(<any>error)
        });
    }

    // }

  }

}