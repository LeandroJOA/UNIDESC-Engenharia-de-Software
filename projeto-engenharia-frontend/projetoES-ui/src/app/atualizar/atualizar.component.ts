import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-atualizar',
  templateUrl: './atualizar.component.html',
  styleUrls: ['./atualizar.component.css']
})
export class AtualizarComponent implements OnInit {

  produto: any;

  constructor(
    private http: HttpClient,
    private route: ActivatedRoute
    ) { }

  ngOnInit(): void {
    const codigo = this.route.snapshot.params['codigo'];
    console.log(codigo);
    this.buscarCodigo(codigo);
  }

  buscarCodigo(codigo) {
    this.http.get(`http://localhost:8080/rest/produto/${codigo}`)
      .subscribe(resultado => this.produto = resultado);
  }

  adicionarProduto(produto) {

    this.http.post('http://localhost:8080/rest/produto', JSON.stringify(this.produto))
      .subscribe(
        resultado => {
          console.log(resultado);
        }
      );
  }
}
