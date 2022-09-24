import { coerceStringArray } from '@angular/cdk/coercion';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { data } from 'jquery';
import { UserFullDetailDTO } from 'src/models/user-full-detail-dto';
import { UserSessionDTO } from 'src/models/user-session-dto';
import { UserServiceService } from './service/user-service.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'anularclient';
  user !:UserSessionDTO|null;
  isAdmin:boolean =false;
  userFull !:UserFullDetailDTO;

  isEditingProfile:boolean = false;;
  constructor(private router: Router, private userService:UserServiceService)
  {
    
  }
  hasLogin()
  {
    return(this.user ==null)? false:true ;
  }
  ngOnInit(): void {
    let text =  localStorage.getItem('uDTO');
    
    if(text != null){
      
      this.user = JSON.parse( text);
      if(this.user != null)
      {

        if(this.user.firstName =="ADMIN" && this.user.lastName =="ADMIN")
        {
          this.isAdmin=true;
          //console.log("admin")
          //this.router.navigate(['/admin']);
          
        }
        else{
          this.isAdmin=false;
        }
      }

    
    }
  }
  onUpdateProfile()
  {
    this.userService.updateById(this.userFull ,this.userFull.id).subscribe(data=>{
      console.log("udpated");
      this.onEditProfile();    
    });
  }
  onEditProfile()
  {
    if(this.user != null)
    {
      this.userService.getUserById(this.user.id).subscribe(data=>{
        this.userFull= data;
        console.log(this.userFull)
        this. isEditingProfile =! this.isEditingProfile;
        console.log(this.isEditingProfile)
      });
    }
  }
  onLogOut()
  {
    localStorage.removeItem('uDTO');
    this.user =null ;
    location.reload();
  }

  checkIfLogin()
  {
    let text =  localStorage.getItem('uDTO');
    console.log(text);
    if(text != null){
      
      this.user = JSON.parse( text);
      if(this.user != null)
      {

        if(this.user.firstName =="ADMIN" && this.user.lastName =="ADMIN")
        {
          this.isAdmin=true;
          //console.log("admin")
          //this.router.navigate(['/admin']);
          
        }
        else{
          this.isAdmin=false;
        }
      }
      return true ;
    }
    else return false;
    
  }
}
