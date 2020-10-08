import { Injectable, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
// import { Combination } from '@model';

import { ServiceGeneric } from '../generic';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { Message } from '@model';

@Injectable()
export class MessageService extends ServiceGeneric<any> {

    constructor(@Inject(HttpClient) public http: HttpClient) {
        super(http);
        this.newURL = this.newURL + 'socket';
        // this.initializeWebSocketConnection();
    }

    // constructor() {
    //     this.initializeWebSocketConnection();
    //   }
      public stompClient;
      public msg = [];
      
      initializeWebSocketConnection(id:number) {
        // const serverUrl = 'http://localhost:8081/socket';
        console.log(this.newURL)
        const ws = new SockJS(this.newURL);
        this.stompClient = Stomp.over(ws);
        const that = this;
        // tslint:disable-next-line:only-arrow-functions
        this.stompClient.connect({}, function(frame) {
          that.stompClient.subscribe('/message', (message) => {
            if (message.body) {
              that.msg.push(message.body);
            }
            
            that.openSocket(id);
          });
        });
      }
      
      sendMessage(message:Message) {
        this.stompClient.send('/app/send/message' , {}, message);
      }

      
    openSocket(id) {
        // if (this.isLoaded) {
            // this.isCustomSocketOpened = true;
            this.stompClient.subscribe('/socket-publisher/' + id, (message) => {
                // // console.log(message)
                this.handleResult(message);
            });
        // }
    }

    
    handleResult(message) {
        console.log(message)
        // if (message.body) {
        //     const messageResult: Message = JSON.parse(message.body);
        //     // // console.log(messageResult);
        //     //   this.messages.push(messageResult);
        //     const mensaje = new Mensaje(0, messageResult.date,
        //         messageResult.message,
        //         new General(Number(messageResult.fromId)), new General(Number(messageResult.toId)), false);
        //     this.onMessageReceived(messageResult.fromId, mensaje);
        //     // this.toastr.success('new message recieved', null, {
        //     // 'timeOut': 3000
        //     // });
        // }
    }

    
}
