import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ContactComponent } from "./entities/contact/contact.component";

const routes: Routes = [
    {path: 'contacts', component: ContactComponent},
    {path: '**', pathMatch: 'full', redirectTo: 'contacts'}
];

@NgModule({
    imports: [RouterModule.forRoot(routes, { useHash: true })],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
