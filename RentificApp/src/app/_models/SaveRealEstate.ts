import { ImageModel } from "./ImageModel";
import { RealEstate } from "./real-estate";
import { StateEnum } from "./stateEnum";

export class SaveRealEstate {
    name: string;
    price: number;
    address: string;
    country: string;
    city: string;
    description: string;
    isReservated: boolean;
    staffId: number;
    state: StateEnum;
    imageModel: ImageModel;

  constructor(realEstate: RealEstate, imageModel: ImageModel) {
    this.name = realEstate.name;
    this.price = realEstate.price;
    this.address = realEstate.address;
    this.country = realEstate.country;
    this.city = realEstate.city;
    this.description = realEstate.description;
    this.isReservated = realEstate.isReservated;
    this.staffId = realEstate.staffId;
    this.state = realEstate.state;
    this.imageModel = imageModel;
  }

}