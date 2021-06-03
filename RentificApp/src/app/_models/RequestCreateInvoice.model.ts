export class RequestCreateInvoice {
    private realEstateId: number;
    private userId: number;

    constructor(userId: number, realEstateId: number) {
        this.realEstateId = realEstateId;
        this.userId = userId;          
    }
}