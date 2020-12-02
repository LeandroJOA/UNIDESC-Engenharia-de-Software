import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';

@Component({
  selector: 'app-fabricante-listagem',
  templateUrl: './fabricante-listagem.component.html',
  styleUrls: ['./fabricante-listagem.component.css']
})
export class FabricanteListagemComponent implements OnInit {

  fornecedores: any = [];

  constructor(
    private http: HttpClient,
    private confirmationService: ConfirmationService,
    private messageService: MessageService
  ) { }

  ngOnInit(): void {
    this.pesquisarFornecedor();
  }

  confirmarExclusao(fornecedor: any) {

    this.confirmationService.confirm({
      target: event.target,
      message: 'Tem certeza que deseja excluir este fornecedor?',
      icon: 'pi pi-trash',
      accept: () => {
        this.excluirFornecedor(fornecedor);
      },
      reject: () => {
        this.messageService.add({ severity: 'warn', summary: 'CANCELADO', detail: 'Exclusão cancelada!' });
      }
    });
  }

  mensagem() {
    this.messageService.add({ severity: 'success', summary: 'SUCESSO', detail: 'Produto Excluido!' });
  }

  pesquisarFornecedor() {
    this.http.get(`http://localhost:8080/rest/fornecedor`)
      .subscribe(resultado => this.fornecedores = resultado);
  }

  excluirFornecedor(fornecedor: any) {
    this.http.delete(`http://localhost:8080/rest/fornecedor/${fornecedor.codigo}`)
      .subscribe(
        resultado => {
          this.pesquisarFornecedor();
          this.mensagem();
        }
      );
  }

}
