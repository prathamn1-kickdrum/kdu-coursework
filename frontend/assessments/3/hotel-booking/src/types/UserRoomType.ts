import { IRoomType } from "./HotelTypes";

export interface IUserRoom {
    addOns: string[];
    userRoom: IRoomType | undefined;
    cost: number;
    bookingDays: number;
}