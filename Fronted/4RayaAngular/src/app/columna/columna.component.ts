import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-columna',
  templateUrl: './columna.component.html',
  styleUrls: ['./columna.component.css']
})
export class ColumnaComponent {
  static turno=0;

  constructor(private http: HttpClient){

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
   contador=5;

   addToken(){
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
       
    }
    this.http.post("http://localhost:8080/","").
    subscribe((response)  => {console.log(response);
    });
   }
}
