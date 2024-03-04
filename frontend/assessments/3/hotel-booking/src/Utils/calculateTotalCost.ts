import { IRoomType } from "../types/HotelTypes"

const calculateTotalCost = (userRoom:IRoomType,userAddOns:string[],bookingDays: number) => {
  
    let totalCost = parseInt(userRoom.costPerNight);
    userRoom.addOns.forEach((addOn) => {
        if(userAddOns.includes(addOn.name))
        totalCost+=parseInt(addOn.cost);
    })
    return totalCost*bookingDays;
}

export default calculateTotalCost;