import {Injectable} from '@angular/core';
import {EntityService} from '../../entities/entityService';
import {HttpClient} from '@angular/common/http';
import {Customer, CustomerPreview} from '../../entities/customer';
import {Tariff, TariffPerCall, TariffPerTime, TariffPreview} from '../../entities/tariff';
import {Observable} from 'rxjs';

@Injectable()
export class CustomerService extends EntityService<Customer, CustomerPreview> {

    constructor(http: HttpClient, urlPath: string) {
        super(http, urlPath);
    }

    createCustomer(entity: Customer): Observable<RequestResponse<CustomerPreview>> {
        return this.http.post<RequestResponse<CustomerPreview>>(`${this.urlPath}/create/customer/`, entity);
    }

    changeCustomerPerTime(entity: Customer): Observable<RequestResponse<Customer>> {
        return this.http.post<RequestResponse<Customer>>(`${this.urlPath}/change/time/`, entity);
    }

    changeCustomerPerCall(entity: Customer): Observable<RequestResponse<Customer>> {
        return this.http.post<RequestResponse<Customer>>(`${this.urlPath}/change/call/`, entity);
    }
}
