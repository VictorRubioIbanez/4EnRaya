import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Usuario } from './models/usuario';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css']
})

export class UsuarioComponent {


  show = false;

  constructor(private http: HttpClient,
              private _router: Router) {
  }

  onSubmit(usersData: {userName: string, 
                       password: string})
  {
    // Limpia el nombre de usuario almacenado en caché y manda los datos del usuario al controlador del servidor
    localStorage.clear();
    var usuario = new Usuario(usersData.userName, usersData.password);
    this.http.post("http://localhost:8080/player",usuario)
    .subscribe((response) => {
      // Si no coinciden usuario y contraseña muestra el aviso de error
      if(response == false){
        this.show = true
      }
      // Si coinciden usuario y contraseña o se registró un nuevo usuario, guarda en caché el nombre y redirige
      // al componente de partida
      else{
        localStorage.setItem('Usuario',usuario.getUserName());
        this._router.navigate(['/partida'])
      }
      //!response ? this.show = true:  this._router.navigate(['/partida']);
    });
  }
}
