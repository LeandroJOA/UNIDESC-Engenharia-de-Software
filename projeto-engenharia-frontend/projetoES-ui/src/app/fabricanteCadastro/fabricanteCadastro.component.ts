import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-fabricanteCadastro',
  templateUrl: './fabricanteCadastro.component.html',
  styleUrls: ['./fabricanteCadastro.component.css']
})
export class FabricanteCadastroComponent implements OnInit {

  fornecedor: any = {};

  constructor(
    private http: HttpClient,
    private router: Router,
    private messageService: MessageService
  ) {
    this.router = router;
  }

  ngOnInit(): void {
  }

  adicionaFornecedor(fornecedor) {
    this.http.post('http://localhost:8080/rest/fornecedor', JSON.stringify(this.fornecedor))
      .subscribe(
        resultado => {
          console.log(this.fornecedor);
          this.mensagem();
          this.router.navigate(['/', 'fornecedor']);
        }, erro => {
          if (erro.status === 500) {
            this.mensagemErro();
          }
        }
      );
  }

  mensagem() {
    this.messageService.add({ severity: 'success', summary: 'SUCESSO', detail: 'Fornecedor adicionado!' });
  }

  mensagemErro() {
    this.messageService.add({ severity: 'error', summary: 'ERROR', detail: 'Campos obrigatorios não preenchidos!' });
  }

}
