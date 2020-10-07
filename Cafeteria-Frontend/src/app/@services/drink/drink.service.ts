import { Injectable, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Drink } from '@model';

import { ServiceGeneric } from '../generic';

@Injectable()
export class DrinkService extends ServiceGeneric<Drink> {

    constructor(@Inject(HttpClient) public http: HttpClient) {
        super(http);
        this.newURL = this.newURL + 'drink';
    }
}
