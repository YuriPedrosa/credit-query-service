import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Credito } from '../models/credito.model';
import { map, Observable } from 'rxjs';
import { APP_API_TOKEN } from '../config/app-api.token';

@Injectable({ providedIn: 'root' })
export class HttpCreditosService {
    private readonly API = inject(APP_API_TOKEN).API;

    constructor(private http: HttpClient) { }

    buscarPorNumeroNfse(numeroNfse: string): Observable<Credito[]> {
        return this.http.get<Credito[]>(`${this.API}/${numeroNfse}`);
    }

    buscarPorNumeroCredito(numeroCredito: string): Observable<Credito[]> {
        return this.http
            .get<Credito>(`${this.API}/credito/${numeroCredito}`)
            .pipe(
                map(credito => credito ? [credito] : [])
            );
    }
}