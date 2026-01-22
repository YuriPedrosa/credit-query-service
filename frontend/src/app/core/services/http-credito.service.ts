import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Credito } from '../models/credito.model';
import { map, Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class HttpCreditosService {
    private readonly API = 'http://localhost:8080/api/creditos'; // mantido assim apenas para fins de exemplo

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