import { Injectable, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Client, Manager, Operator, Supervisor } from '@model';

import { ServiceGeneric } from '../generic';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable()
export class ClientService extends ServiceGeneric<Client> {

    constructor(@Inject(HttpClient) public http: HttpClient) {
        super(http);
        this.newURL = this.newURL + 'client';
    }
    
    getSupport(): Observable<Client> {
      return this.http.get<Client>(this.newURL + '/getSupport', this.options)
        .pipe(
          catchError(this.handleError)
        );
    }
    
    login(username: string,password?:string|""): Observable<Client> {
        // const body = new Client()`username=${username}&password=${password}`;

        let json = JSON.stringify(new Operator(0,username,false,password));
      return this.http.post<Client>(this.newURL + '/login',json, this.optionsPost)
        .pipe(
          catchError(this.handleError)
        );
    }
    
    
    loginClient(username: string): Observable<Client> {
        // const body = new Client()`username=${username}&password=${password}`;

        let json = JSON.stringify(new Client(0,username,false));
      return this.http.post<Client>(this.newURL + '/loginClient',json, this.optionsPost)
        .pipe(
          catchError(this.handleError)
        );
    }

    
  saveOperator(data: Operator): Observable<Operator> {
    let json = JSON.stringify(data);
    return this.http.post<Operator>(this.newURL + '/saveOperator', json, this.optionsPost)
      //.map(this.extractData)
      .pipe(
        catchError(this.handleError)
      );
  }
  
  saveSupervisor(data: Supervisor): Observable<Supervisor> {
    let json = JSON.stringify(data);
    return this.http.post<Supervisor>(this.newURL + '/saveSupervisor', json, this.optionsPost)
      //.map(this.extractData)
      .pipe(
        catchError(this.handleError)
      );
  }

  saveManager(data: Manager): Observable<Manager> {
    let json = JSON.stringify(data);
    return this.http.post<Manager>(this.newURL + '/saveManager', json, this.optionsPost)
      //.map(this.extractData)
      .pipe(
        catchError(this.handleError)
      );
  }


}
