import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ConsultaCreditoFiltro, TipoConsulta } from '../../../core/models/credito-filtro.model';

@Component({
  selector: 'credito-filtro',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './credito-filtro.component.html',
  styleUrls: ['./credito-filtro.component.scss'],
})
export class CreditoFiltroComponent {
  tipo: TipoConsulta = 'NFSE';
  valor = '';

  @Output()
  buscar = new EventEmitter<ConsultaCreditoFiltro>();

  onBuscar(): void {
    if (!this.valor.trim()) return;

    this.buscar.emit({
      tipo: this.tipo,
      valor: this.valor.trim(),
    });
  }
}
