import { OrderItemsModel } from "./OrderItemsModel"
import { OrderTasksModel } from "./OrderTasksModel";

export class OrderDetailsModel{
    public id : String;
    public createdTime : String;
    public completedTime : String;
    public items : Array<OrderItemsModel>;
    public orderComplete: boolean;
    public packingStationId : number;
    public pendingItems : Array<OrderItemsModel>;
    public status : String;
    public tasks : Array<OrderTasksModel>;


    // public timeTaken : number;
    // public totalWeight : number;
     //have to create tasks model ???????????

}