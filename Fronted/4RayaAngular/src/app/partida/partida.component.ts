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

  constructor(private PartidaService:PartidaService, 
    private router:Router,
    private activatedRoute:ActivatedRoute,
    private data:FichaService,
    private http:HttpClient) {}

  ngOnInit(): void {

    this.suscription=this.data.currentUser.subscribe(user=>this.userName=user)
    this.cargarPartidas();
    if(this.userName=="default"){
      swal.fire('<strong>Sesión caducada</strong>', `Te devolvemos a la pantalla de login`, 'success', ).then((result) =>
      
      this.router.navigate(["/usuario"])
        
        );
   }
   
  }


  abrirPartida(idT:number){
    this.data.changeId(idT)
    this.data.changeUser(this.userName)
    if(this.game.playerOne!=this.userName){
     if(this.game.playerTwo=="" || this.game.playerTwo==null) {
      this.game.playerTwo=this.userName;
     }
      
      this.http.post("http://localhost:8080/game/playerTwo/"+idT+"/"+this.userName,"").subscribe();
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
       swal.fire('<strong>Nueva Partida</strong>', `Juego ${this.game.playerOne}  creado con exito`, 'success', ).then((result) =>
      
      this.cargarPartidas()
        
        );
      
       

    }
    )
  }
  

  delete(game:Game):void{
    swal.fire({
      title: '<strong>¿Estás seguro de borrar la partida?</strong>',
      text: `Seguro que desea eliminar la partida con la ID: ${game.id} y el nombre de usuario: ${game.playerOne}`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',

      confirmButtonText: '¡Sí, elimínalo!',
      cancelButtonText: 'Cancélalo, por favor'
    }).then((result) => {
      if (result.value) {
        
        this.PartidaService.delete(game.id).subscribe(
          response=>{
            this.partidas = this.partidas?.filter(game => game!== game)
            this.cargarPartidas();

        swal.fire(
          '<strong>¡Partida eliminada!</strong>',
          'La partida ha sido eliminado',
          'success'
        )
      }
        )
    }
    })

  }

}
