import { HttpClient } from '@angular/common/http';
import {Injectable} from '@angular/core';
import {EntityService} from '../../entities/entityService';
import {Tariff, TariffPerCall, TariffPerTime, TariffPreview} from '../../entities/tariff';
import {Observable} from 'rxjs';

@Injectable()
export class TariffService extends EntityService<Tariff, TariffPreview> {

    constructor(http: HttpClient, urlPath: string) {
        super(http, urlPath);
    }

    createTariffPerTime(entity: TariffPerTime): Observable<RequestResponse<TariffPreview>> {
        return this.http.post<RequestResponse<TariffPreview>>(`${this.urlPath}/create/time`, entity);
    }

    createTariffPerCall(entity: TariffPerCall): Observable<RequestResponse<TariffPreview>> {
        return this.http.post<RequestResponse<TariffPreview>>(`${this.urlPath}/create/call`, entity);
    }

    changeTariffPerTime(entity: Tariff): Observable<RequestResponse<Tariff>> {
        return this.http.post<RequestResponse<TariffPerTime>>(`${this.urlPath}/change/time/`, entity);
    }

    changeTariffPerCall(entity: Tariff): Observable<RequestResponse<Tariff>> {
        return this.http.post<RequestResponse<TariffPerCall>>(`${this.urlPath}/change/call/`, entity);
    }
}
