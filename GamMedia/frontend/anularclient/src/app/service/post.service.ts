import { Injectable } from '@angular/core';



import { HttpClient } from '@angular/common/http';  
import { Observable } from 'rxjs';  





@Injectable({
  providedIn: 'root'
})
export class PostService {

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
    "create",

    getFile:this.apiUrl+
    "downloadFile/",
    
    getPost:this.apiUrl,

    getPostSection:this.apiUrl+
    "homePage/section",
  }
  
  constructor(private http:HttpClient) { }
  getPost(id:String) :Observable<any>
  {
    return this.http.get(this.httpLink.getPost+id);
  }
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

  getFileById( id:String )
  {
    return this.http.get(this.httpLink.getFile+id);
  }

  getPostSection(request:any): Observable<any>
  {
    //section?page=1&size=3
    return this.http.get(this.httpLink.getPostSection+
      "?page="+request['page']+"&size="+request['size'])
  }
  // getALlPostWithouIncludingtMedia(): Observable<any> {  
  //   return this.http.get(`${this.baseUrl}`+'students-list');  
  // }  
}
