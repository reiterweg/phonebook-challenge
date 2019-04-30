import { HttpErrorResponse, HttpResponse } from "@angular/common/http";
import { Component, OnInit } from '@angular/core';
import { AlertService } from "ngx-alerts";
import { Contact } from "./contact.model";
import { ContactService } from "./contact.service";

@Component({
    selector: 'app-contact',
    templateUrl: './contact.component.html'
})
export class ContactComponent implements OnInit {
    contacts: Contact[];
    searchFilter: string = '';

    constructor(
        protected contactService: ContactService,
        private alertService: AlertService
    ) {
    }

    ngOnInit() {
        this.search();
    }

    search() {
        this.contactService.get(this.searchFilter).subscribe(
            (response: HttpResponse<Contact[]>) => {
                this.contacts = response.body;
            },
            (response: HttpErrorResponse) => this.alertService.danger(response.message)
        );
    }

}
