import { FichaService } from './../ficha.service';
import { Component, OnInit } from '@angular/core';
import { PartidaService } from './partida.service';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Game } from '../game';
import swal from "sweetalert2";
import { Subscription } from 'rxjs';




@Component({
  
  selector: 'app-partida',
  templateUrl: './partida.component.html',
  styleUrls: ['./partida.component.css']
})
export class PartidaComponent implements OnInit{


  userName: string ;

  suscription:Subscription;


  public game:Game = new Game();


  public partidas:Game[] | undefined;

  constructor(private PartidaService:PartidaService, private router:Router,private activatedRoute:ActivatedRoute,private data:FichaService) {}

  ngOnInit(): void {

    this.suscription=this.data.currentUser.subscribe(user=>this.userName=user)
    this.cargarPartidas();
    if(this.userName=="default"){
      swal.fire('Sesión caducada', `Volver a la pantalla de inicio`, 'success', ).then((result) =>
      
      this.router.navigate(["/usuario"])
        
        );
   }
   
  }


  abrirPartida(idT:number){
    this.data.changeId(idT)
    if(this.game.playerOne!=this.userName){
      this.game.playerTwo=this.userName;
    }
    this.router.navigate(["/tablero"])
    

  }

  

  cargarPartidas():void{

    this.PartidaService.getPartidas().subscribe(partidas => {
      this.partidas=partidas
      console.log(partidas);
    })

  }
  

  public create():void{
    this.game.playerOne=this.userName;
    this.PartidaService.create(this.game).subscribe(
      game => 
      {
        
       // window.location.reload();
       swal.fire('Nuevo Partida', `Juego ${this.game.playerOne}  creado con exito`, 'success', ).then((result) =>
      
      this.cargarPartidas()
        
        );
      
       

    }
    )
  }
  

  delete(game:Game):void{
    swal.fire({
      title: 'Estas seguro de borrar la partida',
      text: `Seguro que desea eliminar la partida con la id:${game.id} y el nombre de usuario:${game.playerOne}`,
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
