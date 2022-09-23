import { Component, OnInit } from '@angular/core';
import { map, post } from 'jquery';

import { Post } from 'src/models/post';
import { UserSessionDTO } from 'src/models/user-session-dto';
import { PostService } from '../service/post.service';
import { NgModel } from '@angular/forms';
import { FileService } from '../service/file.service';
import { DomSanitizer } from '@angular/platform-browser';
import { Observable, Observer } from 'rxjs';

@Component({
  selector: 'app-post-feed',
  templateUrl: './post-feed.component.html',
  styleUrls: ['./post-feed.component.css']
})
export class PostFeedComponent implements OnInit {

  ListOfPost: Post[] = [];
  user !:UserSessionDTO;
  selectImageFile! : File;


  editingPost!:Post ;
  isEditing:boolean = false;
  constructor(
    private postService:PostService,
    private fileService:FileService,
    private domSanitizer:DomSanitizer ){

  }
  // myReader.onloadend = (e) => {
  //   this.base64Image = this.domSanitizer.bypassSecurityTrustUrl(myReader.result);
  //   console.log(this.base64Image);
  // }

  ngOnInit() {  
  
    this.postService.getAllPostIncludingMedia().subscribe(data =>{  
      this.ListOfPost = data ;

    })  
    let text =  localStorage.getItem('uDTO');
    if(text !=null){
      this.user = JSON.parse( text);
    }
    else{
      alert("please log in ")
    }
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

  createPost(title:HTMLInputElement ,msg:HTMLTextAreaElement)
  {
    console.log("creating post");
    let  post:Post = new Post();
    post.title = title.value;
    post.message= msg.value;
    let text =  localStorage.getItem('uDTO');
    if(text != null){
      let user :UserSessionDTO= JSON.parse( text);
      post.userId =user.id; 
      this.postService.createPost(post).subscribe(data =>{  
        console.log(data);  
        let postId:any = data;
        this.createFile(postId);

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
  getBase64Image(img: HTMLImageElement) {
    // We create a HTML canvas object that will create a 2d image
    var canvas = document.createElement("canvas");
    canvas.width = img.width;
    canvas.height = img.height;
    var ctx = canvas.getContext("2d");
    if(ctx != null){

      // This will draw image    
      ctx.drawImage(img, 0, 0);
    }
    // Convert the drawn image to Data URL
    var dataURL = canvas.toDataURL("image/png");
 return dataURL.replace(/^data:image\/(png|jpg);base64,/, "");
 }
  getBase64ImageFromURL(url: string) {
    return Observable.create((observer: Observer<string>) => {
      // create an image object
      let img = new Image();
      img.crossOrigin = 'Anonymous';
      img.src = url;
      if (!img.complete) {
          // This will call another method that will create image from url
          img.onload = () => {
          observer.next(this.getBase64Image(img));
          observer.complete();
        };
        img.onerror = (err) => {
           observer.error(err);
        };
      } else {
          observer.next(this.getBase64Image(img));
          observer.complete();
      }
    });
 }

  getPostFeed()
  {
    console.log("get post feed")
    this.postService.getAllPostIncludingMedia().subscribe(data =>{  
      this.ListOfPost = data ;
 
    })  ;
    this.ListOfPost.forEach(element => {
      // element.safeUrl =  this.domSanitizer.bypassSecurityTrustUrl(element.imageUrl);
      // console.log(element.safeUrl);
      console.log(element);
      // this.fileService.getById(element.fileId).subscribe(data=>{
      //   console.log(data);
      // });

     });
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
