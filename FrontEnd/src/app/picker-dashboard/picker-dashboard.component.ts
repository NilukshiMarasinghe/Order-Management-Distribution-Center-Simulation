import { Component, OnInit } from "@angular/core";
import { PackerDashboardService } from "../services/packer-dashboard.service";
import { WorkerDetailsModel } from "../models/WorkerDetailsModel";
import { MessageService } from "primeng/api";
import { PackingStationModel } from "../models/PackingStationModel";
import { PackerTasksModel } from "../models/PackerTasksModel";
import { OrderDetailsModel } from "../models/OrderDetailsModel";
import { OrderItemsModel } from "../models/OrderItemsModel";
import { AdminDashoardService } from "../services/admin-dashboard.service";

@Component({
  selector: "app-picker-dashboard",
  templateUrl: "./picker-dashboard.component.html",
  styleUrls: ["./picker-dashboard.component.css"],
})
export class PickerDashboardComponent implements OnInit {
  public loadingMask: boolean = false;
  public allWorkers: WorkerDetailsModel[] = new Array<WorkerDetailsModel>();
  public currentUser = new WorkerDetailsModel();
  public packingStation: PackingStationModel[] = new Array<
    PackingStationModel
  >();
  public packerTasks: PackerTasksModel[] = new Array<PackerTasksModel>();
  public orders: OrderDetailsModel[] = new Array<OrderDetailsModel>();
  public currentOrder = new OrderDetailsModel();
  public currentPackingStation: String = "Not Available";
  public currentItemList: OrderItemsModel[] = Array<OrderItemsModel>();
  public pickerID = parseInt(localStorage.getItem("USER_ID"));
  public metricsArr = [];

  constructor(
    public packerService: PackerDashboardService,
    private messageService: MessageService,
    private dashboardService: AdminDashoardService,
  ) {}

  ngOnInit(): void {
    this.getAllWorkers();
    this.getAllPackingStations();
    this.getTasksList();
    this.getAllOrders();
    this.generateMap();
  }

  getAllWorkers() {
    this.loadingMask = true;
    this.packerService.getAllWorkers().subscribe(
      (res) => {
        this.allWorkers = res.content;
        this.getLoggedUserDet();
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

  getAllPackingStations() {
    this.loadingMask = true;
    this.packerService.getAllPackingCounters().subscribe(
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

  getTasksList() {
    this.loadingMask = true;
    let userId = localStorage.getItem("USER_ID");
    this.packerService.getTasks(userId).subscribe(
      (res) => {
        this.packerTasks = res;
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
    this.packerService.getAllOrders().subscribe(
      (res) => {
        this.orders = res;
        console.log("This is orders >>>", this.orders);
        this.getCurrentOrder();
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

  getLoggedUserDet() {
    var currUserID = parseInt(localStorage.getItem("USER_ID"));

    this.allWorkers.forEach((workers) => {
      if (workers.id == currUserID) {
        this.currentUser = workers;
      }
    });
  }

  getCurrentOrder() {
    let currWorkerID = parseInt(localStorage.getItem("USER_ID"));
    this.loadingMask = true;
    this.orders.forEach((order) => {
      order.tasks.forEach((task) => {
        if (task.workerId == currWorkerID) {
          this.currentOrder = order;
          return;
        }
      });
    });
    this.getCurrentPackingStation();
    this.currentItemList = this.currentOrder.items;
    this.loadingMask = false;
  }

  getCurrentPackingStation() {
    this.packingStation.forEach((station) => {
      if (station.id == this.currentOrder.packingStationId) {
        this.currentPackingStation = station.name;
      }
    });
  }

  generateMap(){

      let workerID = localStorage.getItem("USER_ID")   
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
  
  
}
