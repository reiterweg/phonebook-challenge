import { HttpErrorResponse, HttpResponse } from "@angular/common/http";
import { Component, ElementRef, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { AlertService } from "ngx-alerts";
import { Contact } from "./contact.model";
import { ContactService } from "./contact.service";
import { NgForm } from '@angular/forms';

@Component({
    selector: 'app-contact-edit',
    templateUrl: './contact-edit.component.html'
})
export class ContactEditComponent implements OnInit {

    @ViewChild('btnClose') btnClose: ElementRef;
    @ViewChild('contactForm') contactForm: NgForm;
    @Output() created = new EventEmitter();
    contact: Contact;
    isSaving: boolean;

    constructor(
        protected contactService: ContactService,
        private alertService: AlertService
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.contact = new Contact();
    }

    save() {
        this.isSaving = true;
        this.contactService
            .create(this.contact)
            .subscribe(
                (response: HttpResponse<Contact>) => this.onSaveSuccess(response),
                (response: HttpErrorResponse) => this.onSaveError(response)
            );
    }

    protected onSaveSuccess(response: HttpResponse<Contact>) {
        this.isSaving = false;
        this.created.emit();
        this.contactForm.reset();
        this.btnClose.nativeElement.click();
        this.alertService.success('Contact was successfully created');
    }

    protected onSaveError(response: HttpErrorResponse) {
        this.isSaving = false;
        this.alertService.danger('There was an error creating Contact');
    }

}
