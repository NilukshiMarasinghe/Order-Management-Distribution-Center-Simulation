import { Component, OnInit } from "@angular/core";
import { PackerDashboardService } from "../services/packer-dashboard.service";
import { WorkerDetailsModel } from "../models/WorkerDetailsModel";
import { MessageService } from "primeng/api";
import { PackingStationModel } from "../models/PackingStationModel";
import { PackerTasksModel } from "../models/PackerTasksModel";
import { OrderDetailsModel } from "../models/OrderDetailsModel";
import { OrderItemsModel } from "../models/OrderItemsModel";

@Component({
  selector: "app-packer-dashboar",
  templateUrl: "./packer-dashboar.component.html",
  styleUrls: ["./packer-dashboar.component.css"],
})
export class PackerDashboarComponent implements OnInit {
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

  constructor(
    public packerService: PackerDashboardService,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    this.getAllWorkers();
    this.getAllPackingStations();
    this.getTasksList();
    this.getAllOrders();
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
}
