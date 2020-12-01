import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent {

  constructor(private http: HttpClient) { }

  produto = {
    descricao: '',
    preco: '',
    estoque: ''
  };

  adicionarProduto(produto) {

    this.http.post('http://localhost:8080/rest/produto', JSON.stringify(this.produto))
      .subscribe(
      resultado => {
         console.log(resultado);
      }
    );
  }
}
