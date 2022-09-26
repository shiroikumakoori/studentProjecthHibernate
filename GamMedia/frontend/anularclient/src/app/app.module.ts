import { BrowserModule } from '@angular/platform-browser';  
import { NgModule } from '@angular/core';  
import { AppRoutingModule } from './app-routing.module';  
import { AppComponent } from './app.component';  
import { FormsModule, ReactiveFormsModule } from '@angular/forms';  
import { HttpClientModule } from '@angular/common/http';  
import {DataTablesModule} from 'angular-datatables';
import { PostFeedComponent } from './post-feed/post-feed.component';
import { LoginComponent } from './login/login.component';  
import {MatPaginatorModule} from '@angular/material/paginator'
import {MatInputModule} from '@angular/material/input';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatTableModule} from '@angular/material/table';

import { environment } from 'src/environments/environment';

import {MatButtonModule} from '@angular/material/button';

import {MatCardModule} from '@angular/material/card';

import {MatIconModule} from '@angular/material/icon';

import {  MatDialog, MatDialogModule } from '@angular/material/dialog';
import { AdminComponent } from './admin/admin.component';
import { SafePipe } from './safe.pipe';
import { PostComponent } from './post/post.component';



@NgModule({
  declarations: [
    AppComponent,
    PostFeedComponent,
    LoginComponent,
    AdminComponent,
    SafePipe,
    PostComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,  
    ReactiveFormsModule,  
    HttpClientModule,  
    BrowserAnimationsModule,
    MatButtonModule,
    MatCardModule,
    MatIconModule,
    MatDialogModule,
    MatPaginatorModule,
    MatInputModule,
    MatTableModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
