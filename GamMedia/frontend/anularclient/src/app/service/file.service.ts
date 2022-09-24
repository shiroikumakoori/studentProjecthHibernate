import { Injectable } from '@angular/core';


import { HttpClient, HttpHeaders } from '@angular/common/http';  
import { Observable } from 'rxjs';  


@Injectable({
  providedIn: 'root'
})
export class FileService {



  private  apiUrl = "http://localhost:8080/postService/";

  private httpLink = {

    deletePostById: this.apiUrl + 
    "delete/",

    update: this.apiUrl+
    "update/",

    get: this.apiUrl+
    "downloadFile/",

    save: this.apiUrl + 
    "uploadFile/"
  }
  
  constructor(private http:HttpClient) { }

  create(model :any, id:String){
    return this.http.post(this.httpLink.save+id, model);
  }
  updatePostById(model:any , id:number)
  {
    return this.http.put(this.httpLink.update+id, model);
  }
  updatePost(model:any )
  {
    return this.http.put(this.httpLink.update, model);
  }
  getById(id:String)
  {
    // const httpOptions = {
    //   headers: new HttpHeaders({
    //       'Accept': 'application/json',
    //       'Content-Type': 'application/json'
    //   })
    // };
    console.log(this.httpLink.get+id);
    return this.http.get(this.httpLink.get+id);
  }

  deletePostById(id:String)
  {
    return this.http.delete(this.httpLink.deletePostById+id);
  }

  // getALlPostWithouIncludingtMedia(): Observable<any> {  
  //   return this.http.get(`${this.baseUrl}`+'students-list');  
  // }  
}
