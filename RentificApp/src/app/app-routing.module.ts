import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';

import { LoginComponent } from './login/login.component';
import { RealEstateDetailsComponent } from './real-estate-details/real-estate-details.component';
import { RegisterComponent } from './register';
import { SearchCardComponent } from './search-card/search-card.component';
import { SearchpageComponent } from './searchpage/searchpage.component';
import { AuthGuard } from './_helpers';

const routes: Routes = [
    { path: '', component: RealEstateDetailsComponent},
    { path: 'login', component: LoginComponent },
    { path: 'registracija', component: RegisterComponent },
  { path: 'search', component: SearchpageComponent },
  { path: 'real-estate-details/:realEstate', component: RealEstateDetailsComponent },

    // otherwise redirect to home
    { path: '**', redirectTo: '' }
];

export const AppRoutingModule = RouterModule.forRoot(routes);
