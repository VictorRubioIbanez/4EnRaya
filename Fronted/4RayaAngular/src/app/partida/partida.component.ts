import { Component, OnInit } from '@angular/core';
import { PartidaService } from './partida.service';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Game } from './game';
import swal from "sweetalert2";

@Component({
  selector: 'app-partida',
  templateUrl: './partida.component.html',
  styleUrls: ['./partida.component.css']
})
export class PartidaComponent implements OnInit{

  userName: string = localStorage.getItem('Usuario')!;

  public game:Game = new Game();

  public partidas:Game[] | undefined;

  constructor(private PartidaService:PartidaService, private router:Router,private activatedRoute:ActivatedRoute) {}

  ngOnInit(): void {

    this.cargarPartidas();
   
  }

  public newGame(){
    alert("Nuevo juego hoho");
    //this.http.post("http://localhost:8080/player",this.userName);
  }

  cargarPartidas():void{

    this.PartidaService.getPartidas().subscribe(partidas => {this.partidas=partidas

    })

  }

  public create():void{

    this.game.userName=this.userName;
    this.PartidaService.create(this.game).subscribe(
      game => 
      {
        this.router.navigate(['/tablero'])
        swal.fire('Nuevo Partida', `Juego ${this.game.userName}  creado con exito`, 'success')
    }
    )
  }


  delete(game:Game):void{
    swal.fire({
      title: 'Estas seguro de borrar la partida',
      text: `Seguro que desea eliminar la partida con la id:${game.id} y el nombre de usuario:${game.userName}`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, Eliminalo!'
    }).then((result) => {
      if (result.value) {
        this.PartidaService.delete(game.id).subscribe(
          response=>{
            this.partidas = this.partidas?.filter(game => game!== game)
            this.cargarPartidas();

        swal.fire(
          'Partida Eliminada!',
          'La partida ha sido eliminado',
          'success'
        )
      }
        )
    }
    })

  }

}
