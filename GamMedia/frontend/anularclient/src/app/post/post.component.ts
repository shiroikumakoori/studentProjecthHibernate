import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Post } from 'src/models/post';
import { PostService } from '../service/post.service';


@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {
 post!:Post;
 id!:string;
 
  constructor(
    private activatedroute:ActivatedRoute,
    private postService:PostService) {

     }

  isVideo(fileType:String )
  {
    if(fileType.startsWith('video'))
    {
      return true;
    }
    else return false;
  }

  ngOnInit(): void {
      this.activatedroute.queryParams.subscribe(params => {
        //console.log(params); // { order: "popular" }

        console.log(params['id']); // { order: "popular" }
        this.id  = params['id'];
        this.postService.getPost(this.id).subscribe(data=>{
          console.log(data);
          this.post = data;
          this.post.views += 1;
          this.postService.updatePost(this.post).subscribe(data=>{

          });
      });
      }
    );
    // this.postService.getPost(this.activatedroute.snapshot.params['id']).subscribe(data=>{
    //   console.log(data);
    //   this.post = data;
    // });
  }

}
