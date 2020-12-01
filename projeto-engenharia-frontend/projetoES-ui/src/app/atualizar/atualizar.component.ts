import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-atualizar',
  templateUrl: './atualizar.component.html',
  styleUrls: ['./atualizar.component.css']
})
export class AtualizarComponent implements OnInit {

  produto: any = {
    codigo: '',
    descricao: '',
    estoque: '',
    preco: ''
  };
  fornecedores: any = [];

  constructor(
    private http: HttpClient,
    private route: ActivatedRoute,
    private router: Router,
    private messageService: MessageService
    ) { this.router = router; }

  ngOnInit(): void {
    const codigo = this.route.snapshot.params['codigo'];
    this.buscarCodigo(codigo);
    this.produto.codigo = codigo;
  }

  mensagem() {
    this.messageService.add({ severity: 'success', summary: 'SUCESSO', detail: 'Produto alterado!' });
  }

  mensagemErro() {
    this.messageService.add({ severity: 'error', summary: 'ERROR', detail: 'Campos obrigatorios não preenchidos!' });
  }

  buscarCodigo(codigo) {
    this.http.get(`http://localhost:8080/rest/produto/${codigo}`)
      .subscribe(resultado => this.produto = resultado);

    this.http.get('http://localhost:8080/rest/fornecedor')
      .subscribe(resultado => this.fornecedores = resultado);
  }

  atualizarProduto() {
    console.log(this.produto);
    this.http.put(`http://localhost:8080/rest/produto`, this.produto)
      .subscribe(
        resultado => {
          console.log(resultado);
          this.mensagem();
          this.router.navigate(['/', 'produto']);
        }, erro => {
          if (erro.status === 500) {
            this.mensagemErro();
          }
        }
      );
  }
}
