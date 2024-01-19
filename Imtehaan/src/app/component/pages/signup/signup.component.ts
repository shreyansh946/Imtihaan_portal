import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {


  constructor( private userService:UserService,private Snack:MatSnackBar){}

  public user = {
          username:'',
          password:'',
          firstname:'',
          lastname:'',
          email:'',
          phone:''
  }


  ngOnInit(): void{}

  formSubmit(){
      console.log(this.user)
      if(this.user.username=='' || this.user.username==null)
      {
          this.Snack.open('username is required !!','Ok',{
            duration:3000,
            verticalPosition:'top',
            horizontalPosition:'right'
          });
          return; 
      }



      //adduser: userservice
      this.userService.addUser(this.user).subscribe(

                    (data:any) =>{
                        console.log(data);
                      Swal.fire('Success','User is ' + data.id,'success');
                    },
                    (error) =>{
                        //error
                        console.log(error);
                      //  alert('something went wrong');

                      this.Snack.open('something is not right','Ok',{
                        duration:3000,
                      });

                    }
      )
  }

  



}
