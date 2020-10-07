
// import { throwError as observableThrowError } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../../environments/environment';

// import 'rxjs/Rx';
import { catchError, map } from 'rxjs/operators';
import { Observable } from 'rxjs/Rx';
import { throwError } from 'rxjs';
// import { ErrorHandler } from '@model';

export class ServiceGeneric<T>{

  public newURL: string = "";

  token = { access_token: '' };
  constructor(public http: HttpClient) {

    this.newURL = environment.PathUrl;
  }

  public options = {
    headers: new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'AUTH-TOKEN': this.token.access_token,
      // 'Authorization': 'Bearer ' + this.token.access_token
    }),
    body: "",
    withCredentials: true
  };

  public optionsPost = {
    headers: new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'AUTH-TOKEN': this.token.access_token,
      // 'Authorization': 'Bearer ' + this.token.access_token
    }),
    withCredentials: true
  };

  getAlls(): Observable<T[]> {
    return this.http.get<T[]>(this.newURL + '/getAlls', this.options)
      .pipe(map(response => {
        return response;
      }),
        catchError(this.handleError)
      );
  }

  get(id: string): Observable<T> {
    return this.http.get<T>(this.newURL + '/get/' + id, this.options)
      .pipe(
        catchError(this.handleError)
      );
  }

  delete(id: string): Observable<number> {
    return this.http.delete<number>(this.newURL + '/delete/' + id, this.options)
      .pipe(
        catchError(this.handleError)
      );
  }

  save(data: T): Observable<T> {
    let json = JSON.stringify(data);
    return this.http.post<T>(this.newURL + '/save', json, this.optionsPost)
      //.map(this.extractData)
      .pipe(
        catchError(this.handleError)
      );
  }

  public handleError(error: any) {
    console.error("Hubo un error: ", error);
    let errMsg = error.error;
    return throwError(errMsg);
  }
}  