import { Component } from '@angular/core';
import {TariffService} from '../tariffService';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Configuration} from '../../../entities/configuration';
import {TariffPreview} from '../../../entities/tariff';

@Component({
    selector: 'ngx-tariff-table',
    styleUrls: ['./tariffTable.component.scss'],
    templateUrl: './tariffTable.component.html',
})
export class TariffTableComponent {

    data: Array<TariffPreview>;
    service: TariffService;
    http: HttpClient;
    Configuration = Configuration;

    pageNumber: number = 0;
    pageTotal: number;

    public constructor(http: HttpClient, private router: Router) {
        this.http = http;
        this.service = new TariffService(http, `${Configuration.backHost}/admin/tariff`);
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
        this.router.navigate([`/pages/forms/tariffs/new`]);
    }

    getSingle(event, id: string) {
        this.router.navigate([`/pages/forms/tariffs/single/${id}`]);
    }
}
