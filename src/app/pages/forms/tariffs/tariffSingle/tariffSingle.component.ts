import { Component } from '@angular/core';
import {Tariff, TariffPerCall, TariffPerTime, TariffType} from '../../../entities/tariff';
import {TariffService} from '../tariffService';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Configuration} from '../../../entities/configuration';

@Component({
    selector: 'ngx-tariff-single',
    styleUrls: ['./tariffSingle.component.scss'],
    templateUrl: './tariffSingle.component.html',
})
export class TariffSingleComponent {

    entity: Tariff;
    service: TariffService;
    http: HttpClient;
    tariffType: string;
    public TariffType = TariffType;
    id: string;
    urlParts: string[];

    public constructor(http: HttpClient, private router: Router) {
        this.entity = new Tariff();
        this.urlParts = this.router.url.split('/');
        this.id = this.urlParts[this.urlParts.length - 1];
        this.http = http;
        this.service = new TariffService(http, `${Configuration.backHost}/admin/tariff`);
        this.service.getByIdFull(this.id).subscribe(data => {
            if (data.res === true)
                this.entity = data.data;
            else
                this.router.navigate(['/pages/error']);
        });
    }

    public saveChanges() {
        if (this.entity.type === TariffType.PER_TIME) {
            if (this.entity.timeAvailableDay > 0 && this.entity.price > 0) {
                this.entity.changeTime = null;
                this.entity.creationTime = null;
                this.service.changeTariffPerTime(this.entity).subscribe(data => {
                    if (data.res === true) {
                        this.entity = data.data;
                    } else {
                    }
                });
            }
        } else if (this.entity.type === TariffType.PER_CALL) {
            if (this.entity.callsAvailable > 0 && this.entity.price > 0) {
                this.entity.changeTime = null;
                this.entity.creationTime = null;
                this.service.changeTariffPerCall(this.entity).subscribe(data => {
                    if (data.res === true) {
                        this.entity = data.data;
                    } else {
                    }
                });
            }
        }
    }
}
