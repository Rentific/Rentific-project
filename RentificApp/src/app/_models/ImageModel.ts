import { Byte } from "@angular/compiler/src/util";
import { RealEstate } from "./real-estate";

export class ImageModel {
    id: number;
    name: string;
    type: string;
    picByte: Byte[]; 
    realEstate: RealEstate;

    // constructor(imageId: number, name: string, type: string, picByte: Byte[]) {
    //     this.imageId = imageId;
    //     this.name = name;
    //     this.type = type;
    //     this.picByte = picByte;
    // }

    constructor(imageModel: ImageModel) {
        this.id = imageModel.id;
        this.name = imageModel.name;
        this.type = imageModel.type;
        this.picByte = imageModel.picByte;
        this.realEstate = imageModel.realEstate;
    }
}