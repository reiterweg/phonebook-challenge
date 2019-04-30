import { HttpClientModule } from "@angular/common/http";
import { NgModule } from '@angular/core';
import { FormsModule } from "@angular/forms";
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { AlertModule } from "ngx-alerts";
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './common/header/header.component';
import { ContactEditComponent } from './entities/contact/contact-edit.component';
import { ContactComponent } from './entities/contact/contact.component';

@NgModule({
    declarations: [
        AppComponent,
        HeaderComponent,
        ContactComponent,
        ContactEditComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule,
        BrowserAnimationsModule,
        AlertModule.forRoot({maxMessages: 5, timeout: 3000, position: 'left'})
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
