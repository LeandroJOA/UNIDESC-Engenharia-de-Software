import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { TooltipModule } from 'primeng/tooltip';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { DropdownModule } from 'primeng/dropdown';
import { ConfirmationService } from 'primeng/api';
import { ConfirmPopupModule } from 'primeng/confirmpopup';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';

import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { ProdutoComponent } from './produto/produto.component';
import { VendaComponent } from './venda/venda.component';
import { CadastroComponent } from './cadastro/cadastro.component';
import { AtualizarComponent } from './atualizar/atualizar.component';


const routes: Routes = [
  { path: 'produto', component: ProdutoComponent },
  { path: 'venda', component: VendaComponent },
  { path: 'produto/novo', component: CadastroComponent },
  { path: 'produto/atualizar/:codigo', component: AtualizarComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    ProdutoComponent,
    VendaComponent,
    CadastroComponent,
    AtualizarComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    InputTextModule,
    ConfirmPopupModule,
    InputTextareaModule,
    FormsModule,
    DropdownModule,
    ButtonModule,
    TableModule,
    TooltipModule,
    RouterModule.forRoot(routes),
    BrowserAnimationsModule,
    ToastModule,
  ],
  providers: [ConfirmationService, MessageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
