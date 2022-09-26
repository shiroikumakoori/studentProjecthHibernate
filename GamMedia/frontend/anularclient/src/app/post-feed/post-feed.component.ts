import { Component, OnInit } from '@angular/core';
import { map, post } from 'jquery';

import { Pipe, PipeTransform } from '@angular/core';

import { Post } from 'src/models/post';
import { UserSessionDTO } from 'src/models/user-session-dto';
import { PostService } from '../service/post.service';
import { NgModel } from '@angular/forms';
import { FileService } from '../service/file.service';
import { DomSanitizer } from '@angular/platform-browser';
import { Observable, Observer } from 'rxjs';
import { PageEvent } from '@angular/material/paginator';


@Component({
  selector: 'app-post-feed',
  templateUrl: './post-feed.component.html',
  styleUrls: ['./post-feed.component.css']
})

export class PostFeedComponent implements OnInit  {

  ListOfPost: Post[] = [];
  selectedPost!:Post|null;
  user !:UserSessionDTO;
  selectImageFile! : File;
  totalElements: number = 0;


  editingPost!:Post ;
  isEditing:boolean = false;
  constructor(
    private postService:PostService,
    private fileService:FileService,
    private domSanitizer:DomSanitizer ){

  }


  onSelectingPost(post:Post)
  {
    this.selectedPost = post;
  }

  ngOnInit() {  
    this.onBack();
    //this.getPostFeed()
    let request:any = {};
    request['page'] = 0;
    request['size'] = 3;
    this.getProducts(request);

    let text =  localStorage.getItem('uDTO');
    if(text !=null){
      this.user = JSON.parse( text);
    }
    else{
      alert("please log in ")
    }
  }  

  onBack()
  {
    this.selectedPost= null;
    
  }

  postBelongToUser(post:Post)
  {
    return (post.userId == this.user.id)?true:false;
  }
  isEditingState()
  {
    return this.isEditing;
  }
  onEditPostDone()
  {
    console.log( 
    this.editingPost.id + " "+
    this.editingPost.title+" "+
    this.editingPost.message 
     );
    this.updateEditedPost(this.editingPost);
    this.editingPost!;
    this.isEditing=false;
  }
  onEditPostStart(post:Post)
  {
    this.editingPost= post;
    this.isEditing= true;
  }


  onPhotoSelected(photoSelector:HTMLInputElement)
  {

    if(photoSelector.files != null){

      this.selectImageFile = photoSelector.files[0]
       let fileReader = new FileReader();
       fileReader.readAsDataURL(this.selectImageFile)
       fileReader.addEventListener(
        "loadend",
        ev=>{

          let rebleString;
          if(fileReader.result!=null)
          {

            rebleString=  fileReader.result.toString()
            let postPreviewImage=<HTMLImageElement>document.getElementById("post-preview-image");
            postPreviewImage.src= rebleString

          }
        }
       )
    }
  }

//CRUD *-----------------------------------------------------------------------------------
//file

createFile(id:String)
{
  if(this.selectImageFile != null)
  {
    const formData: FormData = new FormData();
    formData.append('file', this.selectImageFile);

    this.fileService.create(formData,id).subscribe(data =>{  
      console.log(data);  
    })  ;
  }
}


//CRUD *---------------------------------

  createPost(title:HTMLInputElement ,msg:HTMLTextAreaElement, url:HTMLTextAreaElement)
  {
    console.log("creating post");
    let  post:Post = new Post();
    post.title = title.value;
    post.message= msg.value;
    post.urlEmbed= url.value;
    console.log(post.urlEmbed)
    let text =  localStorage.getItem('uDTO');
    if(text != null){
      let user :UserSessionDTO= JSON.parse( text);
      post.userId =user.id; 
      this.postService.createPost(post).subscribe(data =>{  
        console.log(data);  
        let postId:any = data;
        this.createFile(postId);
        alert("post created");
        location.reload();
      })  
    }
    else{
      console.log('need to login ')
    }
  }

  deletePostById(id:String)
  {
    console.log("deleting post");

    this.postService.deletePostById( id).subscribe(data =>{  
      console.log(data);  
      this.getPostFeed();
      //this.deleteMessage=true;  

    //this.dtTrigger.next();  
    })  

  }
  updateEditedPost(post:Post )
  {
    console.log("updating  post: " + 
    post.id + " "+
    post.title+" "+
    post.message 
     );

    this.postService.updatePost(post).subscribe(data =>{  
      console.log(data);  

    })  
  }
  

  getPostFeed()
  {
    console.log("get post feed")
    this.postService.getAllPostIncludingMedia().subscribe(data =>{  
      this.ListOfPost = data ;
   
      this.ListOfPost.forEach(element => {
  
        console.log(element);
  
  
       });
 
    })  ;
  }
  isVideo(fileType:String )
  {
    if(fileType.startsWith('video'))
    {
      return true;
    }
    else return false;
  }
  private getProducts(request:any) {
    console.log(request);
    this.postService.getPostSection(request).subscribe(data => {
        this.ListOfPost = data['content'];
        this.totalElements = data['totalElements'];

        
        this.ListOfPost.forEach(element => {
          console.log(element.urlEmbed)
   
         });
    }
    , error => {
        console.log(error.error.message);
    }
    );
  }
  nextPage(event: PageEvent) {
    let request:any = {};
    request['page'] = event.pageIndex.toString();
    request['size'] = event.pageSize.toString();
    this.getProducts(request);
}
//   downloadFile(filename: string, projectId: number): Observable<any> {
//     return this.http.get(`${this.baseUrl}/file/download/` + filename + '/' + projectId, { responseType: 'blob' }).pipe(map((response)=>{
//         return {
//             filename: 'yourFileName.pdf',
//             data: response.blob()
//         };
//     }));
// }
}
