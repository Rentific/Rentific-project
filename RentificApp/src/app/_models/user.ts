import { Role } from "./role";

export class User {
    id: number;
    firstName:string;
    lastName :string;
    email:string;
    password:string;
    address:string;
    country:string;
    city:string;
    phone:string;
    dateOfBirth:Date;
    token:string;
    role:Role;
}