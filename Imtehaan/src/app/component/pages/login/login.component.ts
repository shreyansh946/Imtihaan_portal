import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent  implements OnInit{

  loginData ={
    username:'',
    password:'',
  }

    constructor(private snack:MatSnackBar,private login:LoginService) {}

  ngOnInit(): void {}


  formSubmit()
  {
        console.log('login button');

        if(this.loginData.username.trim()=='' || this.loginData.username == null)
        {
              this.snack.open('username is required !! ','',{
                duration:3000,
              });
              return;
        }

        if(this.loginData.password.trim()=='' || this.loginData.password == null)
        {
              this.snack.open('password is required !! ','',{
                duration:3000,
              });
              return;
        }

        this.login.generateToken(this.loginData).subscribe(
          (data:any) =>{
            console.log('Success');
            console.log(data);
          },
          (error) =>{
            console.log('Error !');
            console.log(error);
          }

        )

  }

}
