import { Injectable } from "@angular/core";
import { AppParams } from "../app.module";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { catchError, mapTo, tap } from "rxjs/operators";

@Injectable()
export class AuthService {
  constructor(public http: HttpClient) {}

  userAuthorization(payload) {
    localStorage.setItem("TOKEN", AppParams.BASIC_AUTHORIZATION_CODE);
    localStorage.setItem("AUTH_TYPE", AppParams.BASIC_AUTH);

    const opts = {
      headers: new HttpHeaders({}),
    };

    let url = AppParams.BASE_PATH + "oauth/token";
    return this.http.post<any>(url, payload, opts);
  }

  getUserDetails() {
    localStorage.setItem("AUTH_TYPE", AppParams.BEARIER_AUTH);
    let userId = localStorage.getItem("USER_ID");
    let url = AppParams.BASE_PATH + "api/user/" + userId;
    return this.http.get<any>(url);
  }

  getToken() {
    return localStorage.getItem("TOKEN");
  }

  refreshToken() {
    localStorage.setItem("AUTH_TYPE", AppParams.BASIC_AUTH);
    localStorage.setItem("TOKEN", AppParams.BASIC_AUTHORIZATION_CODE);
    
    let refreshToken = localStorage.getItem("REFRESH_TOKEN");

    let refreshTokenData = new FormData();
    refreshTokenData.append("grant_type", "refresh_token");
    refreshTokenData.append("refresh_token", refreshToken);

    let url = AppParams.BASE_PATH + "oauth/token";
    return this.http.post<any>(url, refreshTokenData).pipe(
      tap((res) => {
        console.log("Refresh Token >>>", res);
      })
    );
  }
}
