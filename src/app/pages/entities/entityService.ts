import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

export class EntityService<E, P> {

    http: HttpClient;
    urlPath: string;

    constructor(http: HttpClient, urlPath: string) {
        this.http = http;
        this.urlPath = urlPath;
    }

    getByIdPrev(id: string): Observable<RequestResponse<P>> {
        return this.http.get<RequestResponse<P>>(`${this.urlPath}/single/prev/${id}`);
    }

    getByIdFull(id: string): Observable<RequestResponse<E>> {
        return this.http.get<RequestResponse<E>>(`${this.urlPath}/single/full/${id}`);
    }

    getAllFull(page: number): Observable<RequestResponse<EntitiesPage<Array<E>>>> {
        return this.http.get<RequestResponse<EntitiesPage<Array<E>>>>(`${this.urlPath}/all/full?page=${page}`);
    }

    getAllPrev(page: number): Observable<RequestResponse<EntitiesPage<Array<P>>>> {
        return this.http.get<RequestResponse<EntitiesPage<Array<P>>>>(`${this.urlPath}/all/prev?page=${page}`);
    }

    createEntity(entity: E): Observable<RequestResponse<P>> {
        return this.http.post<RequestResponse<P>>(`${this.urlPath}/create/`, entity);
    }

    changeEntity(entity: E): Observable<RequestResponse<E>> {
        return this.http.post<RequestResponse<E>>(`${this.urlPath}/change/`, entity);
    }
}
