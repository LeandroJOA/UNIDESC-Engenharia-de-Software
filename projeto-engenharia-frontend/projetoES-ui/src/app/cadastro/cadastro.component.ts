import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent implements OnInit {

  fornecedores = [
    { name: 'Teste 1', code: 't1' },
    { name: 'Teste 2', code: 't2' },
  ];

  constructor() { }

  ngOnInit(): void {
  }

}
