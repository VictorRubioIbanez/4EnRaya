import { PresentacionComponent } from './presentacion/presentacion.component';

import { FooterComponent } from './footer/footer.component';
import {NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { TableroComponent } from './tablero/tablero.component';
import { ColumnaComponent } from './columna/columna.component';
import { FichaService } from './ficha.service';
import { UsuarioComponent } from './usuario/usuario.component';
import { PartidaComponent } from './partida/partida.component';

import { HttpClientModule } from '@angular/common/http';

import { ActivatedRoute, Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: '', redirectTo:'/presentacion', pathMatch:'full'},
  { path: "tablero", component: TableroComponent},
  {path: "presentacion", component:PresentacionComponent},
  { path: 'usuario', component: UsuarioComponent },
  { path: 'partida', component: PartidaComponent }
  
];

@NgModule({
  declarations: [
    AppComponent,
    TableroComponent,
    ColumnaComponent,
    FooterComponent,
    PresentacionComponent,
    UsuarioComponent,
    PartidaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
   RouterModule.forRoot(routes),
  ],
  providers: [FichaService],
 
  bootstrap: [AppComponent]
})
export class AppModule { }
