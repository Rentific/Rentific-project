import { User } from "../_models";
import { Role } from "../_models/role";

export const dummyUser1 : User = {
    userId : 1,
    firstName: "TestName",
    lastName: "TestLastName",
    email : "test123@gmail.com",
    password : "Test123!",
    address : "Test",
    city : "Test",
    country : "Test",
    phone : "061234567",
    dateOfBirth : new Date("2012-04-28T14:45:15"),
    access_token : null,
    role : new Role(1, "admin")
}

export const dummyUser2 : User = {
    userId : 2,
    firstName: "TestName2",
    lastName: "TestLastName2",
    email : "test1232@gmail.com",
    password : "Test123!",
    address : "Test",
    city : "Test",
    country : "Test",
    phone : "061234567",
    dateOfBirth : new Date("2012-04-28T14:45:15"),
    access_token : null,
    role : new Role(1, "admin")
}