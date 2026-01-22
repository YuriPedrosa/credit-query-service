export type TipoConsulta = 'NFSE' | 'CREDITO';

export interface ConsultaCreditoFiltro {
  tipo: TipoConsulta;
  valor: string;
}
