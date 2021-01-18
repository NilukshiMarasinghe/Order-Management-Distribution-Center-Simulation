import { Component, OnInit } from "@angular/core";
import { SelectItem } from "primeng/api";
import * as THREE from "three";
import { AuthService } from "../services/auth-service.";
import { AdminDashoardService } from "../services/admin-dashboard.service";
import { MessageService } from "primeng/api";
import { WorkerDetailsModel } from "../models/WorkerDetailsModel";
import { OrderDetailsModel } from "../models/OrderDetailsModel";
import { PackingStationModel } from "../models/PackingStationModel";
import { OverallMetricsModel } from "../models/OverallMetricsModel";
import { DatePipe } from "@angular/common";
import { Chart } from "chart.js";
import { LineCharObj } from "../models/LineChartObj";
import { PackerTasksModel } from "../models/PackerTasksModel";
import { element } from 'protractor';

@Component({
  selector: "app-administrator-dashboard",
  templateUrl: "./administrator-dashboard.component.html",
  styleUrls: ["./administrator-dashboard.component.css"],
})
export class AdministratorDashboardComponent implements OnInit {
  cities1: SelectItem[];
  public loadingMask: boolean = false;
  public selectedOrder: any;
  public lottieConfig: Object;
  public pickers = [];
  public packers = [];
  public workerDetails = new WorkerDetailsModel();
  public orders: OrderDetailsModel[] = new Array<OrderDetailsModel>();
  public orderDet = new OrderDetailsModel();
  public packingStation: PackingStationModel[] = new Array<
    PackingStationModel
  >();
  public packingCounter: String = null;
  public allWorkers: WorkerDetailsModel[] = new Array<WorkerDetailsModel>();
  public orderWorkers: WorkerDetailsModel[] = new Array<WorkerDetailsModel>();
  public overallPerformance: OverallMetricsModel[] = new Array<
    OverallMetricsModel
  >();
  public lineObj: LineCharObj[] = new Array<LineCharObj>();
  public lineModel = new LineCharObj();
  public lineChartLabels = [];
  public lineChartData = [];
  public rgbColorArray = [];

  public orderNoForpicker: String = "Not Available";
  public packingCounerForPicker: String = "Not Available";

  public overallDateRange: any = null;
  public overallPerfChart: any;

  public packerTasks: PackerTasksModel[] = new Array<PackerTasksModel>();

  public verticesMap: String[] = new Array<String>();
  public edgesMap: String[] = new Array<String>();
  public metricsArr = [];

  constructor(
    private authService: AuthService,
    private dashboardService: AdminDashoardService,
    private messageService: MessageService,
    public datepipe: DatePipe
  ) {
    this.cities1 = [
      { label: "Select Order", value: null },
      { label: "#Order01", value: { id: 1 } },
      { label: "#Order02", value: { id: 2 } },
      { label: "#Order03", value: { id: 3 } },
      { label: "#Order04", value: { id: 4 } },
      { label: "#Order05", value: { id: 5 } },
    ];

    this.lottieConfig = {
      path: "assets/lottie/order-box-animation.json",
      autoplay: true,
      loop: true,
    };
  }

  ngOnInit(): void {
    this.getAllPackingStations();
    this.getAllWorkers();
    this.getAllOrders();
  }

  generateFloorPlan() {
    // floorPlan
    this.loadingMask = true;
    this.dashboardService.getAllVerticesEdges().subscribe(
      (res) => {
        this.verticesMap = res.content.vertices;
        this.edgesMap = res.content.edges;

        console.log("Vertices & edges >>> ", this.verticesMap, this.edgesMap);
        this.loadingMask = false;
      },
      (error) => {
        console.log("Error >>>", error);
        this.messageService.add({
          severity: "error",
          summary: "Server Error! Please contact Admin",
        });
        this.loadingMask = false;
      }
    );

    var canvas: HTMLCanvasElement = <HTMLCanvasElement>(
      document.getElementById("floorPlan")
    );
    var ctx: CanvasRenderingContext2D = canvas.getContext("2d");
    ctx.fillStyle = "yellow";
    let metricsArr = [];
    var metrics = { x: 0, y: 0, w: 15, h: 10, name: "" };
    let a = 2;
    for (let i = 0; i < 5; i++) {
      metrics.x = a;
      metrics.y = 2;
      metrics.w = 15;
      metrics.name = "a1." + i;

      a += 20;
      metricsArr.push(metrics);

      ctx.fillRect(metrics.x, metrics.y, metrics.w, metrics.h);
      
    }

    let b = 2;
    for (let i = 0; i < 5; i++) {
      metrics.x = b;
      metrics.y = 15;
      metrics.w = 15;
      metrics.h = 10;
      metrics.name = "a2." + i;

      b += 20;
      metricsArr.push(metrics);

      ctx.fillRect(metrics.x, metrics.y, metrics.w, metrics.h);
      
      ctx.beginPath();
    }

    let c = 2;
    for (let i = 0; i < 5; i++) {
      metrics.x = c;
      metrics.y = 28;
      metrics.w = 15;
      metrics.h = 10;
      metrics.name = "a3." + i;

      c += 20;
      metricsArr.push(metrics);

      ctx.fillRect(metrics.x, metrics.y, metrics.w, metrics.h);
      
      ctx.beginPath();
    }

    let d = 2;
    for (let i = 0; i < 5; i++) {
      metrics.x = d;
      metrics.y = 41;
      metrics.w = 15;
      metrics.h = 10;
      metrics.name = "a4." + i;

      d += 20;
      metricsArr.push(metrics);

      ctx.fillRect(metrics.x, metrics.y, metrics.w, metrics.h);
      
      ctx.beginPath();
    }

    ctx.beginPath();
    ctx.fillStyle = "blue";
    ctx.fillRect(102, 2, 15, 10);

    ctx.beginPath();
    ctx.fillStyle = "blue";
    ctx.fillRect(102, 15, 15, 10);

    ctx.beginPath();
    ctx.fillStyle = "blue";
    ctx.fillRect(102, 28, 15, 10);

    ctx.beginPath();
    ctx.fillStyle = "blue";
    ctx.fillRect(102, 41, 15, 10);

    console.log("Metrics >>>", metricsArr);

    this.openFloorPlan();
  }

  getAllWorkers() {
    this.loadingMask = true;
    this.dashboardService.getAllWorkers().subscribe(
      (res) => {
        this.allWorkers = res.content;
        this.allWorkers.forEach((worker) => {
          if (worker.type == "PICKER") {
            this.pickers.push(worker);
          } else {
            this.packers.push(worker);
          }
        });
        this.loadingMask = false;
      },
      (err) => {
        console.log("Error >>>", err);
        this.messageService.add({
          severity: "error",
          summary: "Server Error! Please contact Admin",
        });
        this.loadingMask = false;
      }
    );
  }

  getWorkerDetailByIDPicker(workerID) {
    this.loadingMask = true;
    this.dashboardService.getWorkerDetailsByID(workerID).subscribe(
      (res) => {
        this.workerDetails = res.content;
        this.loadingMask = false;
        this.getOrderAndPackingStationByPicker(this.workerDetails.id);
        this.generateMapWorker(this.workerDetails.id);
        this.openPickerDetails();
      },
      (err) => {
        console.log("Error >>>", err);
        this.messageService.add({
          severity: "error",
          summary: "Server Error! Please contact Admin",
        });
        this.loadingMask = false;
      }
    );
  }

  getOrderAndPackingStationByPicker(workerID) {
    let stationID = 0;
    this.orders.forEach((orders) => {
      orders.tasks.forEach((tasks) => {
        if (tasks.workerId == workerID) {
          this.orderNoForpicker = orders.id;
          stationID = tasks.packingStationId;
        }
      });

      this.packingStation.forEach((packStations) => {
        if (packStations.id == stationID) {
          this.packingCounerForPicker = packStations.name;
        }
      });
    });
  }

  getWorkerDetailByIDPacker(workerID) {
    this.loadingMask = true;
    this.dashboardService.getWorkerDetailsByID(workerID).subscribe(
      (res) => {
        this.workerDetails = res.content;
        this.loadingMask = false;
        this.getOrderAndPackingStationByPicker(workerID);
        this.openPackerDetails();
      },
      (err) => {
        console.log("Error >>>", err);
        this.messageService.add({
          severity: "error",
          summary: "Server Error! Please contact Admin",
        });
        this.loadingMask = false;
      }
    );
  }

  getTasksListForPacker(workerID) {
    this.loadingMask = true;
    this.dashboardService.getPackerTasks(workerID).subscribe(
      (res) => {
        this.packerTasks = res;
        console.log("This is orders >>>", this.orders);
        this.loadingMask = false;
      },
      (err) => {
        console.log("Error >>>", err);
        this.messageService.add({
          severity: "error",
          summary: "Server Error! Please contact Admin",
        });
        this.loadingMask = false;
      }
    );
  }

  getAllOrders() {
    this.loadingMask = true;
    this.dashboardService.getAllOrders().subscribe(
      (res) => {
        this.orders = res;
        console.log("This is orders >>>", this.orders);
        this.loadingMask = false;
      },
      (err) => {
        console.log("Error >>>", err);
        this.messageService.add({
          severity: "error",
          summary: "Server Error! Please contact Admin",
        });
        this.loadingMask = false;
      }
    );
  }

  getOrderDetail(order) {
    this.loadingMask = true;
    this.orderWorkers = [];
    this.orders.forEach((orders) => {
      if (orders == order) {
        this.orderDet = orders;
        this.packingStation.forEach((packStations) => {
          if (packStations.id == orders.packingStationId) {
            this.packingCounter = packStations.name;
          }
        });

        this.allWorkers.forEach((alWorkers) => {
          orders.tasks.forEach((odTasks) => {
            if (alWorkers.id == odTasks.workerId) {
              this.orderWorkers.push(alWorkers);
            }
          });
        });
      }
    });
    this.loadingMask = false;

    this.openOrderDetail();
  }

  generateMapWorker(workerID) {
    this.metricsArr = [];
    console.log("Worker Map >>>>>");
    var canvas: HTMLCanvasElement = <HTMLCanvasElement>(
      document.getElementById("pickerFloor")
    );
    var ctx: CanvasRenderingContext2D = canvas.getContext("2d");
    ctx.fillStyle = "yellow";
    
    var metrics = { x: 0, y: 0, w: 15, h: 15, name: "" };
    let a = 2;
    for (let i = 0; i < 5; i++) {
      
      metrics.x = a;
      metrics.y = 2;
      metrics.w = 25;
      metrics.name = "a1."+i+"";

      a += 32;

      let mModel = {x: metrics.x, y:metrics.y, w:metrics.w, h:metrics.h, name:metrics.name}
      this.metricsArr.push(mModel);
      ctx.fillRect(metrics.x, metrics.y, metrics.w, metrics.h);
      
    }

    let b = 2;
    for (let i = 0; i < 5; i++) {
      metrics.x = b;
      metrics.y = 22;
      metrics.w = 25;
      metrics.h = 15;
      metrics.name = "a2."+i+"";

      b += 32;
      
      let mModel = {x: metrics.x, y:metrics.y, w:metrics.w, h:metrics.h, name:metrics.name}
      this.metricsArr.push(mModel);
      ctx.fillRect(metrics.x, metrics.y, metrics.w, metrics.h);
  
    }

    let c = 2;
    for (let i = 0; i < 5; i++) {
      metrics.x = c;
      metrics.y = 42;
      metrics.w = 25;
      metrics.h = 15;
      metrics.name = "a3."+i+"";

      c += 32;
      
      let mModel = {x: metrics.x, y:metrics.y, w:metrics.w, h:metrics.h, name:metrics.name}
      this.metricsArr.push(mModel);
      ctx.fillRect(metrics.x, metrics.y, metrics.w, metrics.h);

    }

    let d = 2;
    for (let i = 0; i < 5; i++) {
      metrics.x = d;
      metrics.y = 61;
      metrics.w = 25;
      metrics.h = 15;
      metrics.name = "a4."+i+"";

      d += 32;

      let mModel = {x: metrics.x, y:metrics.y, w:metrics.w, h:metrics.h, name:metrics.name}
      this.metricsArr.push(mModel);
      ctx.fillRect(metrics.x, metrics.y, metrics.w, metrics.h);
      
    }

    ctx.beginPath();
    ctx.fillStyle = "blue";
    ctx.fillRect(160, 2, 25, 15);

    ctx.beginPath();
    ctx.fillStyle = "blue";
    ctx.fillRect(160, 22, 25, 15);

    ctx.beginPath();
    ctx.fillStyle = "blue";
    ctx.fillRect(160, 42, 25, 15);

    ctx.beginPath();
    ctx.fillStyle = "blue";
    ctx.fillRect(160, 61, 25, 15);

    console.log("Metrics Array>>>", this.metricsArr);

    this.dashboardService.getShortestPath(workerID).subscribe(
      (res) => {
        console.log("Shortest Path >>>>", res.content);
        let path = res.content[0].shortedPath;
        let pathsArr = path
          .split("(")
          .join("")
          .split(":")
          .join("")
          .split(")")
          .join("")
          .split(":")
          .join("")
          .split("|");
        let originalPaths = [];
        pathsArr.forEach((element) => {
          var path = element.split(" ");
          originalPaths.push(path[0]);
        });
        console.log("pATH aRRAY >>>>", pathsArr);
        console.log("This is short path >>>", path);
        console.log("Original Path >>>", originalPaths);

        this.metricsArr.forEach((metri) => {
          originalPaths.forEach((ori) => {
            if ((metri.name == ori)) {
              ctx.beginPath();
              ctx.fillStyle = "red";
              ctx.fillRect(metri.x, metri.y, metri.w, metri.h);
            }
          });
        });
      },
      (error) => {
        console.log("Error >>>", error);
        this.messageService.add({
          severity: "error",
          summary: "Server Error! Please contact Admin",
        });
        this.loadingMask = false;
      }
    );

    this.metricsArr.forEach(element=>{
      ctx.font = "6px Arial";
      ctx.fillStyle = "black";
      ctx.strokeText(element.name, element.x, element.y+10);
    })
  }

  getAllPackingStations() {
    this.loadingMask = true;
    this.dashboardService.getAllPackingCounters().subscribe(
      (res) => {
        this.packingStation = res.content;
        this.loadingMask = false;
      },
      (err) => {
        console.log("Error >>>", err);
        this.messageService.add({
          severity: "error",
          summary: "Server Error! Please contact Admin",
        });
        this.loadingMask = false;
      }
    );
  }

  getOverallPerformance() {
    if (this.overallDateRange == null) {
      this.messageService.add({
        severity: "error",
        summary: "Please pick start date & end date",
      });
    } else if (
      this.overallDateRange[0] == null ||
      this.overallDateRange[1] == null
    ) {
      this.messageService.add({
        severity: "error",
        summary: "Please pick start date & end date",
      });
    } else {
      let startDate = null;
      let endDate = null;

      startDate = this.datepipe.transform(
        this.overallDateRange[0],
        "dd-MM-yyyy"
      );

      endDate = this.datepipe.transform(this.overallDateRange[1], "dd-MM-yyyy");
      this.loadingMask = true;
      this.dashboardService.getOverallPerformance(startDate, endDate).subscribe(
        (res) => {
          this.overallPerformance = res;
          this.lineObj = [];
          this.lineChartLabels = [];
          this.lineChartData = [];
          this.rgbColorArray = [];

          var letters = "0123456789ABCDEF";
          var color = "#";
          this.overallPerformance.forEach((obj) => {
            this.lineChartLabels.push(obj.orderId);
            this.lineChartData.push(obj.weightPerSec);
            this.lineModel.label = obj.orderId;
            this.lineModel.data = obj.weightPerSec;

            this.lineObj.push(this.lineModel);
            color = Math.floor(Math.random() * 16777215).toString(16);
            let setHash = "#" + color;
            this.rgbColorArray.push(setHash);
          });

          console.log("Overall Per >>>", this.overallPerformance);
          this.loadingMask = false;
          this.loadChart();
        },
        (error) => {
          this.messageService.add({
            severity: "error",
            summary: "Server Error! Please contact Admin",
          });
          this.loadingMask = false;
        }
      );
    }
  }

  loadChart() {
    console.log("Chart Colors >>>", this.rgbColorArray);
    console.log("Chart Labels >>>", this.lineChartLabels);
    console.log("Chart Data >>>", this.lineChartData);
    let me = this;
    this.overallPerfChart = new Chart("lineOverallPerformanceChart", {
      type: "line",
      options: {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
          xAxes: [
            {
              ticks: {
                beginAtZero: true,
              },
              scaleLabel: {
                display: true,
                labelString: "Order IDs",
              },
            },
          ],
          yAxes: [
            {
              scaleLabel: {
                display: true,
                labelString: "Weight Per Seconds",
              },
            },
          ],
        },
        title: {
          display: true,
        },
        tooltips: {
          enabled: true,
        },
        legend: {
          display: false,
        },
        animation: {
          animateScale: true,
          animateRotate: true,
        },
      },
      data: {
        labels: me.lineChartLabels,
        datasets: [
          {
            backgroundColor: me.rgbColorArray,
            data: me.lineChartData,
          },
        ],
      },
    });

    document.getElementById("chart").style.display = "block";
  }

  getUserDetail() {
    this.authService.getUserDetails().subscribe((res) => {
      console.log("User Details >>>", res);
    });
  }

  getOrderDet() {
    console.log("Order >>> ", this.selectedOrder);
  }

  closeChart() {
    document.getElementById("chart").style.display = "none";
  }

  openOrderDetail() {
    document.getElementById("orderDetails").style.display = "block";
  }

  closeOrderDetails() {
    document.getElementById("orderDetails").style.display = "none";
  }

  openPickerDetails() {
    document.getElementById("pickerDetails").style.display = "block";
  }

  closePickerDetails() {
    document.getElementById("pickerDetails").style.display = "none";
  }

  openPackerDetails() {
    document.getElementById("packerDetails").style.display = "block";
  }

  closePackerDetails() {
    document.getElementById("packerDetails").style.display = "none";
  }

  openFloorPlan() {
    document.getElementById("floorPlanModal").style.display = "block";
  }

  closeFloorPlan() {
    document.getElementById("floorPlanModal").style.display = "none";
  }
}
