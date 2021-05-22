import { StateEnum } from "./stateEnum";

export class RealEstate {
    realEstateId: number;
    name: string;
    price: number;
    address: string;
    country: string;
    city: string;
    description: string;
    isReservated: boolean;
    staffId: number;
    state: StateEnum;
}
