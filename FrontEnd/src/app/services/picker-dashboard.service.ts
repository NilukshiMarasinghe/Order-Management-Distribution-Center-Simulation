import { Injectable } from "@angular/core";
import { AppParams } from "../app.module";
import { HttpClient, HttpHeaders } from "@angular/common/http";

@Injectable()
export class PickerDashboardService {
  constructor(public http: HttpClient) {}
}
