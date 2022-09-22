import { Component, OnInit } from '@angular/core';
import { post } from 'jquery';

import { Post } from 'src/models/post';
import { UserSessionDTO } from 'src/models/user-session-dto';
import { PostService } from '../service/post.service';
import { NgModel } from '@angular/forms';

@Component({
  selector: 'app-post-feed',
  templateUrl: './post-feed.component.html',
  styleUrls: ['./post-feed.component.css']
})
export class PostFeedComponent implements OnInit {

  ListOfPost: Post[] = [];
  user !:UserSessionDTO;


  editingPost!:Post ;
  isEditing:boolean = false;
  constructor(private postService:PostService){

  }

  ngOnInit() {  
    // this.isupdated=false;  
    // this.dtOptions = {  
    //   pageLength: 6,  
    //   stateSave:true,  
    //   lengthMenu:[[6, 16, 20, -1], [6, 16, 20, "All"]],  
    //   processing: true  
    // };     
    this.postService.getAllPostIncludingMedia().subscribe(data =>{  
      this.ListOfPost = data ;
    //this.dtTrigger.next();  
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

//CRUD

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
    //this.dtTrigger.next();  
    })  
  }
}
