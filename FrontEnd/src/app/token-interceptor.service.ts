import { Injectable, Injector } from "@angular/core";
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpErrorResponse,
} from "@angular/common/http";
import { AuthService } from "./services/auth-service.";
import { AppParams } from "./app.module";
import { Observable, throwError, BehaviorSubject } from "rxjs";
import { catchError, filter, take, switchMap } from "rxjs/operators";

@Injectable({
  providedIn: "root",
})
export class TokenInterceptorService implements HttpInterceptor {
  private isRefreshing = false;
  private refreshTokenSubject: BehaviorSubject<any> = new BehaviorSubject<any>(
    null
  );

  constructor(private injector: Injector, public authService: AuthService) {}

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    if (this.authService.getToken()) {
      request = this.addToken(request, this.authService.getToken());
    }

    return next.handle(request).pipe(catchError(error => {
      if (error instanceof HttpErrorResponse && error.status === 401) {
        return this.handle401Error(request, next);
      } else {
        return throwError(error);
      }
    }));
  }

  private addToken(request: HttpRequest<any>, token: string) {
    let authType = localStorage.getItem("AUTH_TYPE");
    return request.clone({
      setHeaders: {
        'Authorization': `${authType} ${token}`
      }
    });
  }

  private handle401Error(request: HttpRequest<any>, next: HttpHandler) {
    if (!this.isRefreshing) {
      this.isRefreshing = true;
      this.refreshTokenSubject.next(null);

      return this.authService.refreshToken().pipe(
        switchMap((token: any) => {
          console.log("Refreshing >>>", token);
          localStorage.setItem("TOKEN", token.access_token);
          localStorage.setItem("REFRESH_TOKEN", token.refresh_token);
          localStorage.setItem("EXPIRES", token.expires_in);
          localStorage.setItem("AUTH_TYPE", AppParams.BEARIER_AUTH);
          this.isRefreshing = false;
          this.refreshTokenSubject.next(token.jwt);
          return next.handle(this.addToken(request, token.access_token));
        }));

    } else {
      return this.refreshTokenSubject.pipe(
        filter(token => token != null),
        take(1),
        switchMap(jwt => {
          return next.handle(this.addToken(request, jwt));
        }));
    }
  }

  // intercept(req, next) {
  //   let authService = this.injector.get(AuthService);
  //   let tokenizedReq = req.clone({
  //     setHeaders: {
  //       Authorization: `${AppParams.AUTHORIZATION_TYPE} ${authService.getToken()}`,
  //     },
  //   });
  //   return next.handle(tokenizedReq);
  // }
}
