import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponentComponent } from './login-component/login-component.component';
import {ButtonModule} from 'primeng/button';
import {InputTextModule} from 'primeng/inputtext';
import {ToastModule} from 'primeng/toast';
import {MessageService} from 'primeng/api';
import { LottieModule } from 'ngx-lottie';
import player from 'lottie-web';
import { AdministratorDashboardComponent } from './administrator-dashboard/administrator-dashboard.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import {ListboxModule} from 'primeng/listbox';
import {CardModule} from 'primeng/card';
import { LoadingMaskComponent } from './loading-mask/loading-mask.component';
import { AuthService } from './services/auth-service.';
import { AdminDashoardService } from './services/admin-dashboard.service';
import { PickerDashboardService } from './services/picker-dashboard.service';
import { TokenInterceptorService } from './token-interceptor.service';
import {CalendarModule} from 'primeng/calendar';
import { DatePipe } from "@angular/common";
import { PickerDashboardComponent } from './picker-dashboard/picker-dashboard.component';
import { PackerDashboarComponent } from './packer-dashboar/packer-dashboar.component';
import { PackerDashboardService } from "./services/packer-dashboard.service";

export function playerFactory() {
  return player;
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponentComponent,
    AdministratorDashboardComponent,
    TopBarComponent,
    LoadingMaskComponent,
    PickerDashboardComponent,
    PackerDashboarComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    ButtonModule,
    InputTextModule,
    LottieModule,
    BrowserAnimationsModule,
    ToastModule,
    [LottieModule.forRoot({ player: playerFactory })],
    ListboxModule,
    CardModule,
    CalendarModule
  ],
  providers: [AuthService,
    AdminDashoardService,
    PickerDashboardService,
    PackerDashboardService,
    DatePipe,
  {
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptorService,
    multi: true
  },
  MessageService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

export const AppParams = Object.freeze({
  BASE_PATH: "http://localhost:10000/", //Raveens Simulation Service
  BASE_PATH_WORKER: "http://localhost:10002/api/", //Raveens Worker Submission Service
  BASE_PATH_ORDER_MANAGEMENT: "http://35.247.190.232:8082/", //Order Management By Pasa
  BASE_PATH_METRICS : "http://35.247.190.232:8081/metric/", //Base path metrics (Overall Performace)
  BASIC_AUTH: "Basic",
  BEARIER_AUTH: "Bearer",
  BASIC_AUTHORIZATION_CODE : "Q1BBUDpDcDQzJiReZmRnZCorISFAI0FnZG80R2Vk"
})
