import { Injectable, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Combination } from '@model';

import { ServiceGeneric } from '../generic';

@Injectable()
export class CombinationService extends ServiceGeneric<Combination> {

    constructor(@Inject(HttpClient) public http: HttpClient) {
        super(http);
        this.newURL = this.newURL + 'combination';
    }
}
