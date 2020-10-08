import {Component, Inject} from '@angular/core';
import { Router } from '@angular/router';
import { Message, Operator } from '@model';
import { Client } from 'src/app/@model/client';
import { ClientService, MessageService } from '@services';
import { LoginComponent } from './login';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'support',
  styleUrls: ['./support.component.css'],
  templateUrl: './support.component.html'
})
export class SupportComponent {

  // title = 'websocket-frontend';
  public input = '';
  public user;
  private responde:Client;
  constructor(@Inject(MessageService) public messageService: MessageService,
  @Inject(ClientService) public _clientService: ClientService,
  public dialog: MatDialog,
  private router: Router) {}

  sendMessage() {
    if (this.input) {
      let message:Message = new Message(0, this.input, new Date(), this.user, this.responde, false);
      this.messageService.sendMessage(message);
      this.input = '';
    }
  }
  
  ngOnInit(): void {
    

    const dialogRef = this.dialog.open(LoginComponent, {
      width: '250px',
      // data: { client:new Client(),isNew:true }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      if(result)
      {
        console.log(result);
        this.user = result;
        this.messageService.initializeWebSocketConnection(result.id);
        if(!this.user.password){
          
      this._clientService.getSupport().subscribe(
        data => {
          console.log(data);
          this.messageService.msg.push(" Hola, soy el supervisor en que puedo ayudarle.");
        },
        error => {
          window.alert(error);
          console.error(<any>error)
        });
        }
        // this.datos.push(result);
        // this.dataSource = new MatTableDataSource(this.datos);
      }
    });

    // if (this.router.url.search("operator") > 0){

    // }
    // else if (this.router.url.search("super") > 0){

    // }
    // else if (this.router.url.search("manager") > 0){

    // }
    // else{
    //   this.user = new Client(0, "Cliente", true);
    //   this.responde = new Operator();
    // }
  }
}