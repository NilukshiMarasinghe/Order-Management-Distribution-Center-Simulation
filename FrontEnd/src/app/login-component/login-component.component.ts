import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { AuthService } from "../services/auth-service.";
import {MessageService} from 'primeng/api';

@Component({
  selector: "app-login-component",
  templateUrl: "./login-component.component.html",
  styleUrls: ["./login-component.component.css"],
})
export class LoginComponentComponent implements OnInit {
  public lottieConfig: Object;
  public loadingMask: boolean = false;
  public username: string = null;
  public password: string = null;

  constructor(public router: Router, private authService: AuthService, private messageService: MessageService ) {
    this.lottieConfig = {
      path: "assets/lottie/economics.json",
      autoplay: true,
      loop: true,
    };
  }

  ngOnInit(): void {}

  login() {
    this.loadingMask = true;
    let loginData = new FormData();
    loginData.append("grant_type", "password");
    loginData.append("username", this.username);
    loginData.append("password", this.password);

    this.authService.userAuthorization(loginData).subscribe(
      (res) => {
        localStorage.setItem("TOKEN", res.access_token);
        localStorage.setItem("REFRESH_TOKEN", res.refresh_token);
        localStorage.setItem("EXPIRES", res.expires_in);
        localStorage.setItem("USER_ID", res.user_id);
        localStorage.setItem("USER_TYPE", res.user_type);

        // this.router.navigateByUrl("/packer-dashboard");
        this.router.navigateByUrl("/picker-dashboard");

        // this.router.navigateByUrl("/packer-dashboard");

        // this.loadingMask = false;
        // if(res.user_role == "Admin"){
        //   this.router.navigateByUrl("/administrator-dashboard");
        // }else if(res.user_role == "Picker"){
        //   this.router.navigateByUrl("/picker-dashboard");
        // }else{
        //   this.router.navigateByUrl("/packer-dashboard");
        // }
        
        
      },
      (err) => {
        console.log("Bad Credentials >>");
        this.loadingMask = false;
        this.messageService.add({severity:'error', summary:err.error.error_description});
      }
    );
    
  }
}
