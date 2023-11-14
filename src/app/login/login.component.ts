import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserService } from '../_services/user.service';
import { UserAuthService } from '../_services/user-auth.service';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  constructor(
    private userService: UserService,
    private userAuthService: UserAuthService,
    private router: Router) { }
  ngOnInit(): void {
    
  }
  login(loginForm:NgForm){
  this.userService.login(loginForm.value).subscribe(
    (Response:any) =>{
      this.userAuthService.setRoles(Response.userInfo.role);
      this.userAuthService.setToken(Response.jwtToken);

      const role = Response.userInfo.role[0].roleName;
      
      if(role === 'Admin'){
        this.router.navigate(['/admin'])
      }else{
        this.router.navigate(['/user'])
      }
    },
    (error) =>{
      console.log(error);
    }
  )
  }
  registerUser(){
    this.router.navigate(['/register'])
  }
}
