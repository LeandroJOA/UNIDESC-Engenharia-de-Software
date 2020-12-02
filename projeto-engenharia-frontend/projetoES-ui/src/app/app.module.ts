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
import { CarouselModule } from 'primeng/carousel';

import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { ProdutolistagemComponent } from './produtolistagem/produtolistagem.component';
import { VendaComponent } from './venda/venda.component';
import { ProdutocadastroComponent } from './produtocadastro/produtocadastro.component';
import { ProdutoatualizarComponent } from './produtoatualizar/produtoatualizar.component';
import { HomeComponent } from './home/home.component';
import { FabricanteListagemComponent } from './fabricante-listagem/fabricante-listagem.component';


const routes: Routes = [
  { path: '', component: HomeComponent},
  { path: 'produto', component: ProdutolistagemComponent },
  { path: 'venda', component: VendaComponent },
  { path: 'produto/novo', component: ProdutocadastroComponent },
  { path: 'produto/atualizar/:codigo', component: ProdutoatualizarComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    ProdutolistagemComponent,
    VendaComponent,
    ProdutocadastroComponent,
    ProdutoatualizarComponent,
    HomeComponent,
    FabricanteListagemComponent
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
    CarouselModule
  ],
  providers: [ConfirmationService, MessageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
