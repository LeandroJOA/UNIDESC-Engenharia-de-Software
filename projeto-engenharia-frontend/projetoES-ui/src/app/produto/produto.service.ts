import { Injectable } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import 'rxjs/add/operator/toPromise';

@Injectable()
export class ProdutoService {

  constructor(private http: HttpClientModule) { }
}
