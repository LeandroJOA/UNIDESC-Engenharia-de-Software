import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-produto',
  templateUrl: './produto.component.html',
  styleUrls: ['./produto.component.css']
})
export class ProdutoComponent implements OnInit {

  lancamentos: any = [];

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.http.get(`http://localhost:8080/rest/produto`)
      .subscribe(resultado => this.lancamentos = resultado);
  }

}
