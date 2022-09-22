import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';  
import { Observable } from 'rxjs';  

@Injectable({
  providedIn: 'root'
})
export class LoginService {


  private  apiUrl = "http://localhost:8080/userService/";


  
  private httpLink = {

    create: this.apiUrl+
    "create",
    login: this.apiUrl + 
    "login/attempLogin"
  }
  
  constructor(private http:HttpClient) { }

  create (model:any){
    return this.http.post(this.httpLink.create,model);
  }
  login(model :any){
    return this.http.post(this.httpLink.login, model);
  }

}
