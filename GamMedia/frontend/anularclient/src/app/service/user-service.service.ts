import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';  
import { Observable } from 'rxjs';  

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {


  private  apiUrl = "http://localhost:8080/userService/";


  
  private httpLink = {
    getAll:this.apiUrl + 
    "all",

    getUser:this.apiUrl,

    deletePostById: this.apiUrl + 
    "delete/",

    getEmployeeDetailById: this.apiUrl + 
    "/api/employee/getEmployeeDetailById",

    updateById: this.apiUrl+
    "update/",

    save: this.apiUrl + 
    "/create"
  }
  
  constructor(private http:HttpClient) { }

  create(model :any){
    return this.http.post(this.httpLink.save, model);
  }
  updateById(model:any , id:String)
  {
    return this.http.put(this.httpLink.updateById, model);
  }

  getAllUser(): Observable<any> {  
    return this.http.get(this.httpLink.getAll);  
  }  
  deleteById(id:String)
  {
    return this.http.delete(this.httpLink.deletePostById+id);
  }
  getUserById(id:String): Observable<any>
  {
    return this.http.get(this.httpLink.getUser+id);  
  }

  // getALlPostWithouIncludingtMedia(): Observable<any> {  
  //   return this.http.get(`${this.baseUrl}`+'students-list');  
  // }  
}
