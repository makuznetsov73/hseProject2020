import {Component} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Tariff, TariffPerCall, TariffPerTime, TariffType} from '../../../entities/tariff';
import {Router} from '@angular/router';
import {Configuration} from '../../../entities/configuration';
import {CustomerService} from '../customerService';
import {Customer} from '../../../entities/customer';
import {TariffService} from '../../tariffs/tariffService';

@Component({
    selector: 'ngx-customer-create',
    styleUrls: ['./customerCreate.component.scss'],
    templateUrl: './customerCreate.component.html',
})
export class CustomerCreateComponent {

    service: CustomerService;
    tariffService: TariffService;
    http: HttpClient;
    public TariffType = TariffType;
    entity: Customer;
    isTariffOk: boolean = false;
    tariff: Tariff;

    public constructor(http: HttpClient, private router: Router) {
        this.entity = new Customer();
        this.http = http;
        this.service = new CustomerService(http, `${Configuration.backHost}/admin/customer`);
        this.tariffService = new TariffService(http, `${Configuration.backHost}/admin/tariff`);
    }

    public addTariff() {
        if (this.entity.id && this.entity.id !== '' && this.entity.password && this.entity.password !== '') {
            if (!this.isTariffOk) {
                this.entity.tariffId = null;
                this.entity.tariffName = null;
                this.entity.tariffType = null;
                this.entity.tariffState = null;
            }
            this.entity.login = this.entity.id;
            this.entity.newTariff = true;
            this.service.createCustomer(this.entity).subscribe(data => {
                if (data.res === true) {
                    this.router.navigate([`/pages/forms/customers/single/${data.data.id}`]);
                } else {
                }
            });
        }
    }

    getTariffTypes() {
        return Object.keys(TariffType).filter(k => typeof TariffType[k as any] === 'string');
    }

    getTariffTypeValue(tariffType) {
        return TariffType[tariffType];
    }

    getTariffInfo() {
        if (this.entity.tariffId !== null && this.entity.tariffId !== '') {
            this.tariffService.getByIdFull(this.entity.tariffId).subscribe(data => {
                if (data.res) {
                    this.entity.tariffName = data.data.tariffName;
                    this.entity.tariffType = data.data.type;
                    this.isTariffOk = true;
                } else {
                    this.isTariffOk = false;
                }
            });
        } else
            this.isTariffOk = false;
    }
}
