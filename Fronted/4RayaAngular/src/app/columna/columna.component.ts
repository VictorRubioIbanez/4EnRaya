import { Component ,Input} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';


@Component({
  selector: 'app-columna',
  templateUrl: './columna.component.html',
  styleUrls: ['./columna.component.css']
})
export class ColumnaComponent {
  static turno=0;

  constructor(private http: HttpClient,
   private _router:Router){

}
  @Input() mov:number;
  ngOnInit(): void {
     
  }
  celdas=  [{id:0,bg: 'white' }, { id:1, bg: 'white' }, { id:2,  bg: 'white'  } ,  {  id:3, bg: 'white' },  {  id:4, bg: 'white' },  { id:5, bg: 'white' },
]
   contador=5;

   addToken(num:number){
     
      var jugador:number=1;
      var res = ColumnaComponent.turno%2
    if(this.contador>=0){
       if(res==0){
         jugador=1;
        this.celdas[this.contador].bg='red'
       }
       if (res!=0){
         jugador=2;
        this.celdas[this.contador].bg='black'
       }
        
        this.contador=this.contador-1;
       ColumnaComponent.turno=ColumnaComponent.turno+1
      localStorage.clear(); 
      localStorage.setItem('Ganador',jugador.toString());
       this.http.post<boolean>("http://localhost:8080/game/"+num+"/"+jugador,"").subscribe(response  => { 
       if(response==false){
       this._router.navigate(['/final']);
     
      }
        
      });
       
        
       
    }
    }}
