import { BrowserModule } from '@angular/platform-browser';  
import { NgModule } from '@angular/core';  
import { AppRoutingModule } from './app-routing.module';  
import { AppComponent } from './app.component';  
import { FormsModule, ReactiveFormsModule } from '@angular/forms';  
import { HttpClientModule } from '@angular/common/http';  
import {DataTablesModule} from 'angular-datatables';
import { PostFeedComponent } from './post-feed/post-feed.component';
import { LoginComponent } from './login/login.component';  

@NgModule({
  declarations: [
    AppComponent,
    PostFeedComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,  
    ReactiveFormsModule,  
    HttpClientModule,  
    DataTablesModule  ,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
