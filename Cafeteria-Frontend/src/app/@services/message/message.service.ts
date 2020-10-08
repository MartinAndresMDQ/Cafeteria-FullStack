import { Injectable, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { ServiceGeneric } from '../generic';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { Message } from '@model';

@Injectable()
export class MessageService extends ServiceGeneric<any> {

    constructor(@Inject(HttpClient) public http: HttpClient) {
        super(http);
        this.newURL = this.newURL + 'socket';
    }

    isLoaded: boolean = false;
      public stompClient;
      public msg = [];
      
      initializeWebSocketConnection(id:number) {
        const ws = new SockJS(this.newURL);
        this.stompClient = Stomp.over(ws);
        const that = this;
        this.stompClient.connect({}, function(frame) {
          that.isLoaded = true;
            that.openSocket(id);
        });
      }
      
      sendMessage(message:Message) {
        this.stompClient.send('/socket-subscriber/send/message' , {}, JSON.stringify(message));
        this.msg.push(message);
      }

      
    openSocket(id) {
        if (this.isLoaded) {
            this.stompClient.subscribe('/socket-publisher/' + id, (message) => {
                this.handleResult(message);
            });
        }
    }

    handleResult(message) {
        if (message.body) {
          const messageResult: Message = JSON.parse(message.body);
          this.msg.push(messageResult);
      }
    }
    
}
