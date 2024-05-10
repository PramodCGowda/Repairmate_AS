import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { LayoutComponent } from './pages/layout/layout.component';
import { HomepageComponent } from './pages/homepage/homepage.component';
import { SingupComponent } from './pages/singup/singup.component';

export const routes: Routes = [
    {
        path: '',  redirectTo:'login', pathMatch: 'full'
    },
    {
        path: 'login',  
        component:LoginComponent
    },
    {
        path: 'singup',  
        component:SingupComponent
    },
    {
        path: '',  
        component:LayoutComponent,
        children:[
            {
                path:'dashboard',
                component:HomepageComponent
            }
        ]
    }
];
