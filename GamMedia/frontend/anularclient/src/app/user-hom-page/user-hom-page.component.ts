import { Component, Input, OnInit } from '@angular/core';
import { UserNameDTO } from 'src/models/user-name-dto';
import { UserPassWordDTO } from 'src/models/user-pass-word-dto';
import { UserSessionDTO } from 'src/models/user-session-dto';

@Component({
  selector: 'app-user-hom-page',
  templateUrl: './user-hom-page.component.html',
  styleUrls: ['./user-hom-page.component.css']
})
export class UserHomPageComponent implements OnInit {


 user !: UserSessionDTO;
  constructor() {

   }

  ngOnInit(): void {
    console.log("test");
    let text = localStorage.getItem('uDTO');
    
    if (text != null) {
      
      this.user = JSON.parse(text);
      if (this.user == null) {
        //boot out 
      }
      if (this.user != null) {
   
      }
    }
    else{
      //boot out 
    }
  }

}
