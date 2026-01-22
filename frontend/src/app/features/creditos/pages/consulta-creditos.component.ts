import { Component } from '@angular/core';
import { Credito } from '../../../core/models/credito.model';
import { CreditosFacade } from '../services/creditos.facade';
import { CreditoFiltroComponent } from '../../components/credito-filtro/credito-filtro.component';
import { CommonModule } from '@angular/common';
import { map, Observable } from 'rxjs';
import { ConsultaCreditoFiltro } from '../../../core/models/credito-filtro.model';

@Component({
    selector: 'app-consulta-creditos', 
    standalone: true,
    imports: [
        CreditoFiltroComponent,
        CommonModule
    ],
    templateUrl: './consulta-creditos.component.html',
    styleUrls: ['./consulta-creditos.component.scss']
})

export class ConsultaCreditosComponent {
    creditos$?: Observable<Credito[]>;
    constructor(private facade: CreditosFacade) { }

    buscarCreditos(filtro: ConsultaCreditoFiltro): void {
        this.creditos$ = this.facade.buscar(filtro);
    }
}