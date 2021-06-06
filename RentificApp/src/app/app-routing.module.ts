import { Routes, RouterModule } from '@angular/router';
import { AddNewRealEstateComponent } from './add-new-real-estate/add-new-real-estate.component';
import { AppComponent } from './app.component';
import { EditRealEstateComponent } from './edit-real-estate/edit-real-estate.component';
import { HomeComponent } from './home/home.component';

import { LoginComponent } from './login/login.component';
import { RealEstateDetailsComponent } from './real-estate-details/real-estate-details.component';
import { RegisterComponent } from './register';
import { SearchCardComponent } from './search-card/search-card.component';
import { SearchpageComponent } from './searchpage/searchpage.component';
import { UserprofileComponent } from './userprofile/userprofile.component';
import { UserprofileeditComponent } from './userprofileedit/userprofileedit.component';
import { AuthGuard } from './_helpers';

const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'add-real-estate', component: AddNewRealEstateComponent },
    { path: 'login', component: LoginComponent },
    { path: 'registracija', component: RegisterComponent },
    { path: 'search', component: SearchpageComponent },
    { path: 'real-estate-details/:realEstateId', component: RealEstateDetailsComponent },
    { path: 'edit-real-estate-details/:realEstate', component: EditRealEstateComponent },
    { path: 'viewprofile', component: UserprofileComponent },
    { path: 'editprofile', component: UserprofileeditComponent },
    { path: '**', redirectTo: '' }
];

export const AppRoutingModule = RouterModule.forRoot(routes);
