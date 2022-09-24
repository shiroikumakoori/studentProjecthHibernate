import { Component, OnInit } from '@angular/core';
import { Post } from 'src/models/post';
import { User } from 'src/models/user';
import { UserSessionDTO } from 'src/models/user-session-dto';
import { FileService } from '../service/file.service';
import { PostService } from '../service/post.service';
import { UserServiceService } from '../service/user-service.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  ListOfPost: Post[] = [];
  listOfUser :UserSessionDTO[] = [];

  editingUser!:UserSessionDTO;
  editingPost!:Post ;
  isEditingUser:boolean = false;

  isEditing:boolean = false;
  constructor(
    private postService:PostService,
    private fileService:FileService,
    private userService:UserServiceService){

  }

  ngOnInit(): void {
    this.postService.getAllPostIncludingMedia().subscribe(data =>{  
      this.ListOfPost = data ;

    })  
    this.userService.getAllUser().subscribe(data=>{
      console.log(data)
      this.listOfUser = data;
    })
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

  onEditUserStart(user:UserSessionDTO)
  {
    this.editingUser = user;
    this.isEditingUser= true;
  }
  onEditUserDone()
  {

    this.updateEditedUser(this.editingUser);
    this.editingUser!;
    this.isEditingUser=false;
  }

  onCancelEditingUser()
  {
    this.isEditingUser=false;
  }
  //CRUD
  onDeleteUserById(id:String )
  {
    this.userService.deleteById(id).subscribe(data=>{
      location.reload();
    })
  }
  deletePostById(id:String)
  {
    console.log("deleting post");

    this.postService.deletePostById( id).subscribe(data =>{  
      console.log(data);  
      location.reload();
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

  updateEditedUser(user:UserSessionDTO)
  {
    this.userService.updateById(user,user.id).subscribe(data=>
    {

    });
  }

  getPostFeed()
  {
    console.log("get post feed")
    this.postService.getAllPostIncludingMedia().subscribe(data =>{  
      this.ListOfPost = data ;
 
    })  ;
    this.ListOfPost.forEach(element => {

     });
  }

}
