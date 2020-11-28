import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { TooltipModule } from 'primeng/tooltip';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { DropdownModule } from 'primeng/dropdown';

import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { ProdutoComponent } from './produto/produto.component';
import { VendaComponent } from './venda/venda.component';
import { CadastroComponent } from './cadastro/cadastro.component';


const routes: Routes = [
  {path: 'produto', component: ProdutoComponent },
  {path: 'venda', component: VendaComponent},
  { path: 'produto/novo', component: CadastroComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    ProdutoComponent,
    VendaComponent,
    CadastroComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    InputTextModule,
    InputTextareaModule,
    DropdownModule,
    ButtonModule,
    TableModule,
    TooltipModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
