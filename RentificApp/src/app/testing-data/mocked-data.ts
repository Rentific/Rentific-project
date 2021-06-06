import { RealEstate } from "../_models/real-estate";

export const dummyRealEstate1  : RealEstate = {
    realEstateId : 1,
    name : "Available real estate",
    price : 120000,
    address : "Test address",
    country : "Test country",
    city: "Test city",
    description : "This is some test description",
    isReservated: false,
    staffId : 1,
    state: 0,
    imageModel: null
}

export const dummyRealEstate2  : RealEstate = {
    realEstateId : 2,
    name : "Not available real estate",
    price : 120000,
    address : "Test address",
    country : "Test country",
    city: "Test city",
    description : "This is some test description",
    isReservated: true,
    staffId : 1,
    state: 0,
    imageModel: null
}