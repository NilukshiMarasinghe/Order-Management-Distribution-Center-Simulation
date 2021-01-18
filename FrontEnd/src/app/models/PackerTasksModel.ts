import {OrderItemsModel} from "./OrderItemsModel";
export class PackerTasksModel{
    public id : number;
    public workerId : String;
    public orderId : String;
    public packingStationId : number;
    public status : String;
    public items : OrderItemsModel[] = new Array<OrderItemsModel>();

}