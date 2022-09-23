import { coerceStringArray } from '@angular/cdk/coercion';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserSessionDTO } from 'src/models/user-session-dto';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'anularclient';
  user !:UserSessionDTO|null;
  isAdmin:boolean =false;
  constructor(private router: Router)
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
