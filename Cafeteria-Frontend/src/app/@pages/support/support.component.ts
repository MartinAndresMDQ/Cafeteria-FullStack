import { Component, DoCheck, Inject, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ClientService, MessageService } from '@services';
import { LoginComponent } from './login';
import { MatDialog } from '@angular/material/dialog';
import { Client, Message } from '@model';

@Component({
  selector: 'support',
  styleUrls: ['./support.component.css'],
  templateUrl: './support.component.html'
})
export class SupportComponent implements OnInit, OnDestroy, DoCheck {

  public input = '';
  public user;
  public responde;
  public clients: Client[] = [];
  public disponible = true;
  public entro = false;
  constructor(@Inject(MessageService) public messageService: MessageService,
    @Inject(ClientService) public _clientService: ClientService,
    public dialog: MatDialog,
    private router: Router) { }

  sendMessage() {
    if (this.input) {

      let mensaje: Message = {
        message: this.input,
        fromId: this.user.id.toString(),
        toId: this.responde.id.toString(),
        date: new Date()
      };
      this.messageService.sendMessage(mensaje);
      this.input = '';
    }
  }

  ngDoCheck() {
    if (!this.entro && this.messageService.msg.length == 1) {
      this.entro = true;
      this.disponible = true;
      this._clientService.get(this.messageService.msg[0].fromId).subscribe(
        data => {
          this.responde = data;

        },
        error => {
          window.alert(error);
          console.error(<any>error)
        });
    }
  }

  ngOnInit(): void {
    const dialogRef = this.dialog.open(LoginComponent, {
      width: '250px',
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.user = result;
        this.messageService.initializeWebSocketConnection(result.id);
        if (!this.user.password) {
          this.entro = true;
          this._clientService.getSupport(this.user.id).subscribe(
            data => {
              this.responde = data;
              if (data == null) {
                this.messageService.msg.push(
                  new Message(" Hola, en estos momentos no contamos con ningun representante que te pueda ayudar, intente mas tarde", null, this.user.id));
                this.disponible = false;
              }

              else if ((<any>data).DTYPE == "Operator") {
                this.messageService.msg.push(
                  new Message(" Hola, soy " + data.name + " el Operator Nivel 1, en que puedo ayudarle.", this.responde.id, this.user.id));
              }
              else if ((<any>data).DTYPE == "Supervisor") {
                this.messageService.msg.push(new Message(" Hola, soy " + data.name + " el Supervisor, en que puedo ayudarle.", this.responde.id, this.user.id));
              }
              else if ((<any>data).DTYPE == "Manager") {
                this.messageService.msg.push(new Message(" Hola, soy " + data.name + " el Gerente, en que puedo ayudarle.", this.responde.id, this.user.id));
              }

            },
            error => {
              window.alert(error);
              console.error(<any>error)
            });
        }
        else
        this.disponible = false;
      }
      else
        this.router.navigate(['/']);
    });
  }

  disconnect() {
    if (this.user)
      this._clientService.disconnect(this.user.id, this.responde ? this.responde.id : 0).subscribe(
        data => {
          this.user = null;
          this.messageService.msg = [];
          this.router.navigate(['/'])
        },
        error => {
          window.alert(error);
          console.error(<any>error)
        });
  }

  ngOnDestroy() {
    this.disconnect();
  }
}