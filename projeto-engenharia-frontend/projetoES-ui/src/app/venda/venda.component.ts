import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-venda',
  templateUrl: './venda.component.html',
  styleUrls: ['./venda.component.css']
})
export class VendaComponent {

  lancamentos: any = [];

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.http.get(`http://localhost:8080/rest/produto`)
      .subscribe(resultado => this.lancamentos = resultado);
  }

}
