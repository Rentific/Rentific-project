import { Role } from "./role";

export class User {
    userId: number;
    firstName:string;
    lastName :string;
    email:string;
    password:string;
    address:string;
    country:string;
    city:string;
    phone:string;
    dateOfBirth:Date;
    access_token:string;
    role:Role;
}
