import { Injectable } from '@angular/core';



import { HttpClient } from '@angular/common/http';  
import { Observable } from 'rxjs';  





@Injectable({
  providedIn: 'root'
})
export class PostService {
  private baseUrl = 'http://localhost:8080/postService/';  
  private  apiUrl = "http://localhost:8080/postService/";

  private httpLink = {
    getAll:this.apiUrl + 
    "homePage/all",

    deletePostById: this.apiUrl + 
    "delete/",

    getEmployeeDetailById: this.apiUrl + 
    "/api/employee/getEmployeeDetailById",

    update: this.apiUrl+
    "update/",

    save: this.apiUrl + 
    "create"
  }
  
  constructor(private http:HttpClient) { }

  createPost(model :any){
    return this.http.post(this.httpLink.save, model);
  }
  updatePostById(model:any , id:number)
  {
    return this.http.put(this.httpLink.update+id, model);
  }
  updatePost(model:any )
  {
    return this.http.put(this.httpLink.update, model);
  }

  getAllPostIncludingMedia(): Observable<any> {  
    return this.http.get(this.httpLink.getAll);  
  }  
  deletePostById(id:String)
  {
    return this.http.delete(this.httpLink.deletePostById+id);
  }

  // getALlPostWithouIncludingtMedia(): Observable<any> {  
  //   return this.http.get(`${this.baseUrl}`+'students-list');  
  // }  
}
