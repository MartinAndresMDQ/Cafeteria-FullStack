import { Injectable, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Additional } from '@model';

import { ServiceGeneric } from '../generic';

@Injectable()
export class AdditionalService extends ServiceGeneric<Additional> {

    constructor(@Inject(HttpClient) public http: HttpClient) {
        super(http);
        this.newURL = this.newURL + 'additional';
    }
}
