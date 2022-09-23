import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';  
import { Observable } from 'rxjs';  

@Injectable({
  providedIn: 'root'
})
export class LoginService {


  private  apiUrl = "http://localhost:8080/userService/";
  private  apiUrl2 = "http://localhost:8080/emailService/";

  
  private httpLink = {

    resetpassword: this.apiUrl2+
    "send/resetPassword",
    create: this.apiUrl+
    "create",
    login: this.apiUrl + 
    "login/attempLogin"
  }
  
  constructor(private http:HttpClient) { }

  create (model:any){
    console.log(this.httpLink.create)
    return this.http.post(this.httpLink.create,model);
  }
  login(model :any){
    return this.http.post(this.httpLink.login, model);
  }
  resetPassword(email:any)
  {
    console.log(this.httpLink.resetpassword+" " + email);
    return this.http.post(this.httpLink.resetpassword, email);
  }

}
