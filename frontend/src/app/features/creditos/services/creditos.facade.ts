import { Injectable } from '@angular/core';
import { HttpCreditosService } from '../../../core/services/http-credito.service';
import { Observable } from 'rxjs';
import { Credito } from '../../../core/models/credito.model';
import { ConsultaCreditoFiltro } from '../../../core/models/credito-filtro.model';

@Injectable({
    providedIn: 'root'
})
export class CreditosFacade {
    constructor(private readonly http: HttpCreditosService) { }

    buscar(filtro: ConsultaCreditoFiltro) {
        return filtro.tipo === 'NFSE'
            ? this.http.buscarPorNumeroNfse(filtro.valor)
            : this.http.buscarPorNumeroCredito(filtro.valor);
    }

}