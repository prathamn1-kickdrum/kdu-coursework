export interface IHotelApiData {
    roomTypes: IRoomType[];
}

export interface IHotelInitialState {
    isLoading: boolean;
    error: undefined | string;
    status: string;
    rooms: IRoomType[];
}

export interface IRoomType {
    id:           number;
    name:         string;
    costPerNight: string;
    currency:     Currency;
    addOns:       IAddOn[];
}

export interface IAddOn {
    name:     string;
    cost:     string;
    currency: Currency;
}

export enum Currency {
    Inr = "INR",
}

export interface IRoomSubset {
    id: number;
    name: string;
    costPerNight: string;
}