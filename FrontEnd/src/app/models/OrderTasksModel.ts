import { OrderItemsModel } from "./OrderItemsModel";

export class OrderTasksModel{
    public id : String;
    public items : Array<OrderItemsModel>;
    public orderId : String;
    public packingStationId: number;
    public status: String;
    public workerId: number;
}