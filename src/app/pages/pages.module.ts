import { NgModule } from '@angular/core';
import {NbCardModule, NbMenuModule} from '@nebular/theme';

import { ThemeModule } from '../@theme/theme.module';
import { PagesComponent } from './pages.component';
import { PagesRoutingModule } from './pages-routing.module';
import { MiscellaneousModule } from './miscellaneous/miscellaneous.module';
import {StartComponent} from './startComponent/start.component';

@NgModule({
  imports: [
    PagesRoutingModule,
    ThemeModule,
    NbMenuModule,
    MiscellaneousModule,
    NbCardModule,
  ],
  declarations: [
    PagesComponent,
    StartComponent,
  ],
})
export class PagesModule {
}
