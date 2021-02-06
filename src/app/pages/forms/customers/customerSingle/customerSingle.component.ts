import {Component} from '@angular/core';
import {Tariff, TariffType} from '../../../entities/tariff';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Configuration} from '../../../entities/configuration';
import {Customer} from '../../../entities/customer';
import {CustomerService} from '../customerService';
import {TariffService} from '../../tariffs/tariffService';

@Component({
    selector: 'ngx-customer-single',
    styleUrls: ['./customerSingle.component.scss'],
    templateUrl: './customerSingle.component.html',
})
export class CustomerSingleComponent {

    entity: Customer;
    service: CustomerService;
    tariffService: TariffService;
    http: HttpClient;
    tariffType: string;
    public TariffType = TariffType;
    id: string;
    urlParts: string[];
    isTariffOk: boolean = false;

    public constructor(http: HttpClient, private router: Router) {
        this.entity = new Customer();
        this.urlParts = this.router.url.split('/');
        this.id = this.urlParts[this.urlParts.length - 1];
        this.http = http;
        this.service = new CustomerService(http, `${Configuration.backHost}/admin/customer`);
        this.tariffService = new TariffService(http, `${Configuration.backHost}/admin/tariff`);
        this.service.getByIdFull(this.id).subscribe(data => {
            if (data.res === true) {
                this.entity = data.data;
                if (this.entity.tariffId) {
                    this.isTariffOk = true;
                }
            } else
                this.router.navigate(['/pages/error']);
        });
    }

    public saveChanges() {
        if (this.entity.tariffType === TariffType.PER_TIME) {
            if (!this.isTariffOk) {
                this.entity.tariffId = null;
                this.entity.tariffName = null;
                this.entity.tariffType = null;
            }
            if (this.entity.tariffState) {
                delete this.entity.tariffState.blocked;
            }
            this.entity.creationTime = null;
            this.entity.changeTime = null;
            this.service.changeCustomerPerTime(this.entity).subscribe(data => {
                if (data.res === true) {
                        this.entity = data.data;
                } else {
                }
            });
        } else if (this.entity.tariffType === TariffType.PER_CALL) {
            if (!this.isTariffOk) {
                this.entity.tariffId = null;
                this.entity.tariffName = null;
                this.entity.tariffType = null;
            }
            if (this.entity.tariffState) {
                delete this.entity.tariffState.blocked;
            }
            this.entity.creationTime = null;
            this.entity.changeTime = null;
            this.service.changeCustomerPerCall(this.entity).subscribe(data => {
                if (data.res === true) {
                        this.entity = data.data;
                } else {
                }
            });
        }
    }

    getTariffInfo() {
        if (this.entity.tariffId !== null && this.entity.tariffId !== '') {
            this.tariffService.getByIdFull(this.entity.tariffId).subscribe(data => {
                if (data.res) {
                    this.entity.tariffName = data.data.tariffName;
                    this.entity.tariffType = data.data.type;
                    this.entity.tariffState = null;
                    this.isTariffOk = true;
                    this.entity.newTariff = true;
                } else {
                    this.isTariffOk = false;
                }
            });
        } else
            this.isTariffOk = false;
    }
}
