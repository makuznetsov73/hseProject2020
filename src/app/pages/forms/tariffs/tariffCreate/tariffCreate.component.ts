import {Component} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {TariffService} from '../tariffService';
import {Configuration} from '../../../entities/configuration';
import {TariffPerCall, TariffPerTime, TariffType} from '../../../entities/tariff';

@Component({
    selector: 'ngx-tariff-create',
    styleUrls: ['./tariffCreate.component.scss'],
    templateUrl: './tariffCreate.component.html',
})
export class TariffCreateComponent {

    service: TariffService;
    http: HttpClient;
    tariffTypeSelected: TariffType;
    public TariffType = TariffType;
    callsAvailable: number;
    timeAvailableDay: number;
    name: string;
    description: string;
    price: number;

    public constructor(http: HttpClient, private router: Router) {
        this.http = http;
        this.service = new TariffService(http, `${Configuration.backHost}/admin/tariff`);
    }

    public addTariff() {
        if (this.tariffTypeSelected === TariffType.PER_TIME) {
            if (this.timeAvailableDay > 0 && this.price > 0) {
                const entity: TariffPerTime = new TariffPerTime();
                entity.type = TariffType.PER_TIME;
                entity.price = this.price;
                entity.description = this.description;
                entity.tariffName = this.name;
                entity.timeAvailableDay = this.timeAvailableDay;
                this.service.createTariffPerTime(entity).subscribe(data => {
                    if (data.res === true) {
                        this.router.navigate([`/pages/forms/tariffs/single/${data.data.id}`]);
                    } else {
                    }
                });
            }
        } else if (this.tariffTypeSelected === TariffType.PER_CALL) {
            if (this.callsAvailable > 0 && this.price > 0) {
                const entity: TariffPerCall = new TariffPerCall();
                entity.type = TariffType.PER_TIME;
                entity.price = this.price;
                entity.description = this.description;
                entity.tariffName = this.name;
                entity.callsAvailable = this.callsAvailable;
                this.service.createTariffPerCall(entity).subscribe(data => {
                    if (data.res === true) {
                        this.router.navigate([`/pages/forms/tariffs/single/${data.data.id}`]);
                    } else {
                    }
                });
            }
        }
    }

    getTariffTypes() {
        return Object.keys(TariffType).filter(k => typeof TariffType[k as any] === 'string');
    }

    getTariffTypeValue(tariffType) {
        return TariffType[tariffType];
    }
}
