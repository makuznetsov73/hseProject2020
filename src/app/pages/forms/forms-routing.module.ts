import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { FormsComponent } from './forms.component';
import { FormInputsComponent } from './form-inputs/form-inputs.component';
import { FormLayoutsComponent } from './form-layouts/form-layouts.component';
import { DatepickerComponent } from './datepicker/datepicker.component';
import { ButtonsComponent } from './buttons/buttons.component';
import {TariffTableComponent} from './tariffs/tariffTable/tariffTable.component';
import {TariffCreateComponent} from './tariffs/tariffCreate/tariffCreate.component';
import {TariffSingleComponent} from './tariffs/tariffSingle/tariffSingle.component';
import {CustomerTableComponent} from './customers/customerTable/customerTable.component';
import {CustomerSingleComponent} from './customers/customerSingle/customerSingle.component';
import {CustomerCreateComponent} from './customers/customerCreate/customerCreate.component';

const routes: Routes = [
  {
    path: '',
    component: FormsComponent,
    children: [
      {
        path: 'customers',
        component: CustomerTableComponent,
      },
      {
        path: 'customers/new',
        component: CustomerCreateComponent,
      },
      {
        path: 'customers/single/:id',
        component: CustomerSingleComponent,
      },
      {
        path: 'tariffs',
        component: TariffTableComponent,
      },
      {
        path: 'tariffs/new',
        component: TariffCreateComponent,
      },
      {
        path: 'tariffs/single/:id',
        component: TariffSingleComponent,
      },
      {
        path: 'inputs',
        component: FormInputsComponent,
      },
      {
        path: 'layouts',
        component: FormLayoutsComponent,
      },
      {
        path: 'buttons',
        component: ButtonsComponent,
      },
      {
        path: 'datepicker',
        component: DatepickerComponent,
      },
    ],
  },
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
  ],
  exports: [
    RouterModule,
  ],
})
export class FormsRoutingModule {
}

