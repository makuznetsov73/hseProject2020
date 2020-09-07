import { NgModule } from '@angular/core';
import {
  NbActionsModule,
  NbButtonModule,
  NbCardModule,
  NbCheckboxModule,
  NbDatepickerModule, NbIconModule,
  NbInputModule,
  NbRadioModule,
  NbSelectModule,
  NbUserModule,
} from '@nebular/theme';

import { ThemeModule } from '../../@theme/theme.module';
import { FormsRoutingModule } from './forms-routing.module';
import { FormsComponent } from './forms.component';
import { FormInputsComponent } from './form-inputs/form-inputs.component';
import { FormsModule as ngFormsModule } from '@angular/forms';
import {TariffTableComponent} from './tariffs/tariffTable/tariffTable.component';
import {TariffCreateComponent} from './tariffs/tariffCreate/tariffCreate.component';
import {TariffSingleComponent} from './tariffs/tariffSingle/tariffSingle.component';
import {CustomerTableComponent} from './customers/customerTable/customerTable.component';
import {CustomerSingleComponent} from './customers/customerSingle/customerSingle.component';
import {CustomerCreateComponent} from './customers/customerCreate/customerCreate.component';

@NgModule({
  imports: [
    ThemeModule,
    NbInputModule,
    NbCardModule,
    NbButtonModule,
    NbActionsModule,
    NbUserModule,
    NbCheckboxModule,
    NbRadioModule,
    NbDatepickerModule,
    FormsRoutingModule,
    NbSelectModule,
    NbIconModule,
    ngFormsModule,
  ],
  declarations: [
    CustomerTableComponent,
    CustomerCreateComponent,
    CustomerSingleComponent,
    TariffTableComponent,
    TariffCreateComponent,
    TariffSingleComponent,
    FormsComponent,
    FormInputsComponent,
  ],
})
export class FormsModule { }
