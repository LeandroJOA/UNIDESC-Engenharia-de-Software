import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ConfirmationService } from 'primeng/api';

@Component({
  selector: 'app-produto',
  templateUrl: './produto.component.html',
  styleUrls: ['./produto.component.css']
})
export class ProdutoComponent implements OnInit {

  produtos: any = [];

  constructor(
      private http: HttpClient,
      private confirmationService: ConfirmationService
    ) { }

  ngOnInit(): void {
    this.pesquisarProduto();
  }

  confirmarExclusao(produto: any) {

    this.confirmationService.confirm({
      target: event.target,
      message: 'Are you sure that you want to proceed?',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.excluirProduto(produto);
      },
      reject: () => {
        //reject action
      }
    });
  }

  pesquisarProduto() {
    this.http.get(`http://localhost:8080/rest/produto`)
      .subscribe(resultado => this.produtos = resultado);
  }

  excluirProduto(produto: any) {
    this.http.delete(`http://localhost:8080/rest/produto/${produto.codigo}`)
      .subscribe(
        resultado => {
          console.log(resultado);
          this.pesquisarProduto();
        }
      );
  }

}
