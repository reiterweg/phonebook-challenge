import { HttpClient, HttpResponse } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { Observable } from "rxjs";
import { map } from "rxjs/operators";
import { IContact } from "./contact.model";


@Injectable({ providedIn: 'root' })
export class ContactService {

    public resourceUrl = '/api/contacts';

    constructor(
        protected http: HttpClient
    ) {
    }

    create(contact: IContact): Observable<HttpResponse<IContact>> {
        return this.http
            .post<IContact>(this.resourceUrl, contact, { observe: 'response' })
            .pipe(map((response: HttpResponse<IContact>) => response));
    }

    get(searchFilter?: string): Observable<HttpResponse<IContact[]>> {
        const params = {};

        if (searchFilter) {
            params['searchFilter'] = searchFilter;
        }

        return this.http
            .get<IContact[]>(this.resourceUrl, { params, observe: 'response' })
            .pipe(map((response: HttpResponse<IContact[]>) => response));
    }

}
