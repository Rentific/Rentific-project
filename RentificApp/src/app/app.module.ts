import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { LoginComponent } from './login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { RegisterComponent } from './register';
import { AlertComponent } from './_components';
import { ErrorInterceptor } from './_helpers';
import { HomeComponent } from './home/home.component';
import { SearchCardComponent } from './search-card/search-card.component';
import { SearchpageComponent } from './searchpage/searchpage.component';
import { RealEstateDetailsComponent } from './real-estate-details/real-estate-details.component';
import { TokenInterceptor } from './token-interceptor/token.interceptor';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MAT_FORM_FIELD_DEFAULT_OPTIONS } from '@angular/material/form-field';
import { ImageModalComponent } from './image-modal/image-modal.component';
import { FormsModule } from '@angular/forms';
import { AddNewRealEstateComponent } from './add-new-real-estate/add-new-real-estate.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    AlertComponent,
    HomeComponent,
    SearchCardComponent,
    SearchpageComponent,
    RealEstateDetailsComponent,
    ImageModalComponent,
    AddNewRealEstateComponent,
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatButtonModule,
    NgbModule,
    MatPaginatorModule,
    FormsModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },
    { provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, useValue: { appearance: 'fill' } }],
  bootstrap: [AppComponent],
  entryComponents: [ImageModalComponent]
})
export class AppModule { }
