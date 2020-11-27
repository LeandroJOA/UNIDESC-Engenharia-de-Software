import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import 'rxjs/add/operator/toPromise';

@Injectable()
export class ProdutoService {

  produtoUrl = 'http://localhost:8080/rest/produto';

  constructor(private http: HttpClient) { }

  pesquisar(): Promise<any> {
    return this.http.get(`${this.produtoUrl}`)
      .toPromise()
      .then(response => {
        console.log(response.json());
      })
  }
}
