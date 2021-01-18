import { Injectable } from "@angular/core";
import { AppParams } from "../app.module";
import { HttpClient, HttpHeaders } from "@angular/common/http";

@Injectable()
export class AdminDashoardService {
  constructor(public http: HttpClient) {}

  getAllWorkers() {
    let url = AppParams.BASE_PATH + "api/worker";

    return this.http.get<any>(url);
  }

  getWorkerDetailsByID(workerID) {
    let url = AppParams.BASE_PATH + "api/worker/" + workerID;

    return this.http.get<any>(url);
  }

  getAllOrders() {
    let url = AppParams.BASE_PATH_ORDER_MANAGEMENT + "order";

    return this.http.get<any>(url);
  }

  getAllPackingCounters() {
    localStorage.setItem("AUTH_TYPE", AppParams.BEARIER_AUTH);
    let url = AppParams.BASE_PATH + "api/map/packingStations";
    return this.http.get<any>(url);
  }

  getOverallPerformance(startDate, endDate) {
    let url =
      AppParams.BASE_PATH_METRICS +
      "weight-processing-time?end=" +
      endDate +
      "&start=" +
      startDate;

    return this.http.get<any>(url);
  }

  getPackerTasks(packerID) {
    let url = AppParams.BASE_PATH_ORDER_MANAGEMENT + "task/worker/" + packerID;
    return this.http.get<any>(url);
  }

  getAllVerticesEdges(){
    let url = AppParams.BASE_PATH + "api/map";
    return this.http.get<any>(url);
  }

  getShortestPath(id){
    let url = AppParams.BASE_PATH_WORKER + "task/getAllByPicker/"+id;
    return this.http.get<any>(url);
  }
}
