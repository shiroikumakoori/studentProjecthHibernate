import { coerceStringArray } from '@angular/cdk/coercion';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { data } from 'jquery';
import { UserFullDetailDTO } from 'src/models/user-full-detail-dto';
import { UserNameDTO } from 'src/models/user-name-dto';
import { UserPassWordDTO } from 'src/models/user-pass-word-dto';
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
  userNameDTO!:UserNameDTO;
  userPasswordDTO !:UserPassWordDTO;

  isEditingProfile:boolean = false;;
  constructor(private router: Router, private userService:UserServiceService)
  {
    this.userNameDTO = new UserNameDTO();
    this.userPasswordDTO = new UserPassWordDTO();
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
        this.userNameDTO.id = this.user.id;
        this.userPasswordDTO.id = this.user.id; 
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
  onUpdateUserName()
  {
    this.userService.updateName(this.userNameDTO ).subscribe(data=>{
      console.log("udpated");
      this.onEditProfile();    
    });
  }
  updateUserPassword()
  {
    console.log(this.userPasswordDTO.password);
    if(this.userPasswordDTO.newPassword != this.userPasswordDTO.newPassword2)
    {
      alert("new password dont match ")
      return ;
    }
    this.userService.updatePassword(this.userPasswordDTO).subscribe(data=>{
      alert("password changed ")
    },(err)=>{
      alert("old password dont match ")
    }
    )
 
    ;
  }
  onEditProfile()
  {
    if(this.user != null)
    {
      this.userService.getUserById(this.user.id).subscribe(data=>{
        this.userNameDTO = data ; 
        console.log(this.userFull)
        this. isEditingProfile =! this.isEditingProfile;
      });
    }
  }
  onLogOut()
  {
    localStorage.removeItem('uDTO');
    this.user =null ;
    this.userService.loginOut().subscribe(data=>{
      
    })
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
        this.userNameDTO.id = this.user.id;
        this.userPasswordDTO.id = this.user.id; 
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
