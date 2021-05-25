import { RealEstate } from "./real-estate";

export class RealEstateResponse {
    realEstateList: RealEstate[];
    totalPages: number;
    currentPage: number;
    totalItems: number;

    constructor(realEstateList: RealEstate[], totalPages: number, currentPage: number,totalItems: number ) {
        this.realEstateList = realEstateList;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.totalItems = totalItems;
    }
}