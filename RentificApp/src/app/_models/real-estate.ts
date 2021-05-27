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

  constructor(realEstate: RealEstate) {
    this.realEstateId = realEstate.realEstateId;
    this.name = realEstate.name;
    this.price = realEstate.price;
    this.address = realEstate.address;
    this.country = realEstate.country;
    this.city = realEstate.city;
    this.description = realEstate.description;
    this.isReservated = realEstate.isReservated;
    this.staffId = realEstate.staffId;
    this.state = realEstate.state;
  }
}
