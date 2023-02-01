import { TableroComponent } from './../tablero/tablero.component';
import { Component, createNgModule } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-columna',
  templateUrl: './columna.component.html',
  styleUrls: ['./columna.component.css']
})

export class ColumnaComponent {

  static turno=0;
  id:number=0;
  columnas= [0,1,2,3,4,5,6]

  public numeroFichas :number =0;

  private urlEndPoint:string ='http://localhost:8080/game'

  constructor(private http: HttpClient, private router:Router,private activatedRoute:ActivatedRoute){



  }
   celdas=  [{
      id:0,
      bg: 'white'
   },
   {
    id:1,
    bg: 'white'
   },
   {
    id:2,
      bg: 'white'
   }
   ,
   {
    id:3,
      bg: 'white'
   },
   {
    id:4,
      bg: 'white'
   },
   {
    id:5,
      bg: 'white'
   },
   ]
    public contador=5;

   addToken(){


      this.numeroFichas++;
    

      console.log(this.numeroFichas);

      
      
    var res = ColumnaComponent.turno%2

    if(this.contador>=0){
       if(res==0){
        this.celdas[this.contador].bg='red'
       }
       if (res!=0){
        this.celdas[this.contador].bg='black'
       }
        
        this.contador=this.contador-1;
       ColumnaComponent.turno=ColumnaComponent.turno+1
       
       console.log( this.contador);
    }
    
   }

   idChange(){
     
     

   }
}
