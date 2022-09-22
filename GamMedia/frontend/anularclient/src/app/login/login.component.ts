import { Component, OnInit } from '@angular/core';
import { LoginService } from '../service/login.service';
import { LoginDTO } from 'src/models/login-dto';
import { UserSessionDTO } from 'src/models/user-session-dto';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor( private loginService:LoginService) { }

  ngOnInit(): void {
  }

  onLogin(u:HTMLInputElement, p:HTMLInputElement)
  {
    let obj:LoginDTO = new LoginDTO();
    obj.userName = u.value;
    obj.password = p.value;
    this.loginService.login(obj).subscribe(data =>{  
      let uDTO  = data ;
      localStorage.setItem('uDTO',JSON.stringify(uDTO));
    //this.dtTrigger.next();  
    })  ;
  }
}
