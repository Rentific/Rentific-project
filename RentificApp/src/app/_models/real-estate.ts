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

  constructor(realEstateId: number, name: string, price: number, address: string, country: string, city: string, description: string, isReservated = false, staffId = 0, state = StateEnum.Namjesten) {
    this.realEstateId = realEstateId;
    this.name = name;
    this.price = price;
    this.address = address;
    this.country = country;
    this.city = city;
    this.description = description;
    this.isReservated = isReservated;
    this.staffId = staffId;
    this.state = state;
  }
}
