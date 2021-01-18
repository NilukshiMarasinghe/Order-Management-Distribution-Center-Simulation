import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import { LoginComponentComponent } from "./login-component/login-component.component";
import { AdministratorDashboardComponent } from "./administrator-dashboard/administrator-dashboard.component";
import { PackerDashboarComponent } from "./packer-dashboar/packer-dashboar.component";
import { PickerDashboardComponent } from "./picker-dashboard/picker-dashboard.component";

const routes: Routes = [
  {
    path: "",
    component: LoginComponentComponent,
  },
  {
    path: "administrator-dashboard",
    component: AdministratorDashboardComponent,
  },
  {
    path: "packer-dashboard",
    component: PackerDashboarComponent,
  },
  {
    path: "picker-dashboard",
    component: PickerDashboardComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
