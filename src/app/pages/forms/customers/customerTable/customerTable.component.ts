import { Component } from '@angular/core';
import {CustomerService} from '../customerService';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Configuration} from '../../../entities/configuration';
import {CustomerPreview} from '../../../entities/customer';

@Component({
    selector: 'ngx-customer-table',
    styleUrls: ['./customerTable.component.scss'],
    templateUrl: './customerTable.component.html',
})
export class CustomerTableComponent {

    data: Array<CustomerPreview>;
    service: CustomerService;
    http: HttpClient;
    Configuration = Configuration;

    pageNumber: number = 0;
    pageTotal: number;

    public constructor(http: HttpClient, private router: Router) {
        this.http = http;
        this.service = new CustomerService(http, `${Configuration.backHost}/admin/customer`);
        this.service.getAllPrev(this.pageNumber).subscribe(data => {
            this.data = data.data.entities;
            this.pageNumber = data.data.pageNumber;
            this.pageTotal = data.data.pageTotal;
        });
    }

    getPage(pageNumber: number) {
        if (pageNumber >= 0) {
            if (pageNumber <= this.pageTotal) {
                this.service.getAllPrev(pageNumber).subscribe(data => {
                    this.data = data.data.entities;
                    this.pageNumber = data.data.pageNumber;
                    this.pageTotal = data.data.pageTotal;
                });
            } else {
                this.service.getAllPrev(this.pageTotal).subscribe(data => {
                    this.data = data.data.entities;
                    this.pageNumber = data.data.pageNumber;
                    this.pageTotal = data.data.pageTotal;
                });
            }
        }
    }

    createNew(event) {
        this.router.navigate([`/pages/forms/customers/new`]);
    }

    getSingle(event, id: string) {
        this.router.navigate([`/pages/forms/customers/single/${id}`]);
    }
}
