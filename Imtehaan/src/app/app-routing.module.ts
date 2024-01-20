import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './component/pages/signup/signup.component';
import { LoginComponent } from './component/pages/login/login.component';
import { HomeComponent } from './component/pages/home/home.component';
import { DashBoardComponent } from './component/pages/admin/dash-board/dash-board.component';
import { UserdashboardComponent } from './component/pages/user/userdashboard/userdashboard.component';

const routes: Routes = [
  {
    path:'signup',
    component:SignupComponent,
    pathMatch:"full",
  },
  {
    path:'login',
    component:LoginComponent,
    pathMatch:"full",
  },
  {
    path:'home',
    component:HomeComponent,
    pathMatch:"full",
  },
  {
    path:'admin',
    component:DashBoardComponent,
    pathMatch:'full',
  },  
  {
    path:'user-dashboard',
    component:UserdashboardComponent,
    pathMatch:'full',
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
