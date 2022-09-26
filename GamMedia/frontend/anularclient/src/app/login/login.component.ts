import { Component, OnInit } from '@angular/core';
import { LoginService } from '../service/login.service';
import { LoginDTO } from 'src/models/login-dto';
import { UserSessionDTO } from 'src/models/user-session-dto';
import { UserFullDetailDTO } from 'src/models/user-full-detail-dto';
import { EmailValidator } from '@angular/forms';
import { first } from 'rxjs';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
  state:STATES = STATES.LOGIN

  constructor( private loginService:LoginService) { }

  ngOnInit(): void {
  }

  onChangeToForgetPassword()
  {
    this.state = STATES.FORGOT_PASSWORD;
  }
  onChangeToLogin()
  {
    this.state = STATES.LOGIN
  }
  onChangeToCreate()
  {
    this.state = STATES.REGISTER;
  }
  isLogin()
  {
    return(this.state== STATES.LOGIN)
  }
  isCreate()
  {
   
    return (this.state == STATES.REGISTER)
  }

  isForgetPassword(){
    return (this.state == STATES.FORGOT_PASSWORD)
  }
  resetPassword(e:HTMLInputElement)
  {
    if(e.value != null){
      let email:Email  = new Email();
      email.recipient= e.value;
      this.loginService.resetPassword(email).subscribe(data=>{
        //console.log(data);
        alert("password reserted")
        location.reload();
      });
    }
  }
  //CRUD

  onCreateAcc(
    u:HTMLInputElement , p:HTMLInputElement, pc:HTMLInputElement ,
    e:HTMLInputElement, ln:HTMLInputElement, fn:HTMLInputElement
    )
  {
    if(p.value == pc.value)
    {
      let user :UserFullDetailDTO = new UserFullDetailDTO();

      if(u.value == "")
      {
        alert("user Name cannot be blank")
        return ;
      }
      if(p.value != pc.value)
      {
        alert("password dont match")
        return;
      }
      if(e.value =="")
      {
        alert("email cannot be blank")
        return;
      }
      if(ln.value =="")
      {
        alert("please type a last name")
        return; 
      }
      if(fn.value=="")
      {
        alert("please type a first name")
        return; 
      }
      user.userName = u.value;
    

      user.password = p.value;
      
      user.lastName = ln.value;
      user.firstName = fn.value;
      user.email  = e.value;
      this.loginService.create(user).subscribe(data =>{  
        alert("account created")
        location.reload();
      }) ;;
    }
    else
    {
      alert("confirm passoword and password dont match ");
    }
  }
  onLogin(u:HTMLInputElement, p:HTMLInputElement)
  {
    let obj:LoginDTO = new LoginDTO();
    obj.userName = u.value;
    obj.password = p.value;
    this.loginService.login(obj).subscribe(data =>{  
      console.log(data);
      let uDTO  = data ;
      localStorage.setItem('uDTO',JSON.stringify(uDTO));
    //this.dtTrigger.next();  
    location.reload();
    },(err)=>{
      alert("user name or password dont match ")
    }
    )  ;
  }
}
export enum STATES{
  LOGIN,
  REGISTER,
  FORGOT_PASSWORD,
}
export class Email{
  public recipient!:String;
}