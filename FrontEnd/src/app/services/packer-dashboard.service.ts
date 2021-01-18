import { Injectable } from "@angular/core";
import { AppParams } from "../app.module";
import { HttpClient, HttpHeaders } from "@angular/common/http";

@Injectable()
export class PackerDashboardService {
  constructor(public http: HttpClient) {}

  getAllWorkers() {
    let url = AppParams.BASE_PATH + "api/worker";

    return this.http.get<any>(url);
  }

  getAllPackingCounters() {
    localStorage.setItem("AUTH_TYPE", AppParams.BEARIER_AUTH);
    let url = AppParams.BASE_PATH + "api/map/packingStations";
    return this.http.get<any>(url);
  }

  getTasks(packerID) {
    let url = AppParams.BASE_PATH_ORDER_MANAGEMENT + "task/worker/" + packerID;
    return this.http.get<any>(url);
  }

  getAllOrders() {
    let url = AppParams.BASE_PATH_ORDER_MANAGEMENT + "order";

    return this.http.get<any>(url);
  }
}
