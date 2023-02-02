import { Datos } from './../Datos';
import { FichaService } from './../ficha.service';
import swal from "sweetalert2";

import { Component ,Input} from '@angular/core';

import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Game } from '../game';
import { Client } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';


@Component({
  selector: 'app-columna',
  templateUrl: './columna.component.html',
  styleUrls: ['./columna.component.css']
})


export class ColumnaComponent {

  private  client: Client;

  
  @Input() idPartida:number;
  @Input() mov:number;
  public numeroFichas :number =0;
  
  userName: string ;
  turn:number=1;
  suscription:Subscription;

  constructor(
   private _router:Router,
   private data:FichaService){

}
  
   ngOnInit(): void {
    this.suscription=this.data.currentUser.subscribe(user=>this.userName=user)

    this.client=new Client();
        this.client.webSocketFactory= ()=>{
          return new SockJS("http://localhost:8080/game-websockect");
       }

        this.client.activate();

        this.client.onConnect=(frame)=>{
          this.client.subscribe('/tablero/movimiento', e =>{
            let g:Game = JSON.parse(e.body) as Game;
            if(g.id!=null){
              if(g.id==this.idPartida){
                if(g.board[0][this.mov]!="0"){
                  this.numeroFichas=6
                }
            for(let i=0; i<6;i++){
                if(g.board[i][this.mov]=="0"){
                  this.celdas[i].bg='white'
                }
                if(g.board[i][this.mov]==g.playerOne){
                  this.celdas[i].bg='red'
                }
                if(g.board[i][this.mov]==g.playerTwo){
                  this.celdas[i].bg='black'
                }
              }
              this.turn++;
            if(g.status=="Ganador1"){
              swal.fire('LA PARTIDA SE ACABO', `El ganador es `+g.playerOne.toString(), 'success',).then(result =>this._router.navigate(["/partida"]));
            }
            if(g.status=="Ganador2"){
              swal.fire('LA PARTIDA SE ACABO', `El ganador es `+g.playerTwo.toString(), 'success',).then(result =>this._router.navigate(["/partida"]));
            }
            if(g.status=="Empate"){
              swal.fire('LA PARTIDA SE ACABO', `La partida acabo en empate `, 'success',).then(result =>this._router.navigate(["/partida"]));
            }
            



            }
            } 
        });
        }
       

      }

  celdas=  [{id:0,bg: 'white' }, { id:1, bg: 'white' }, { id:2,  bg: 'white'  } ,  {  id:3, bg: 'white' },  {  id:4, bg: 'white' },  { id:5, bg: 'white' },
]
   contador=5;
   addToken(num:number){
       var datos: Datos= new Datos(this.idPartida.toString(), this.userName,this.mov,this.turn);
       this.client.publish({destination:"/app/movimiento",body: JSON.stringify(datos)});
      
    }}

