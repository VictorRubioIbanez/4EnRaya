import { Component } from '@angular/core';


@Component({
  selector: 'app-tablero',
  templateUrl: './tablero.component.html',
  styleUrls: ['./tablero.component.css']
})
export class TableroComponent {
    columnas= [0,1,2,3,4,5,6]
    goBackImage:string='/assets/imagenes/back.png'
    hideFire=true;

    public fireworks(){
      if(this.hideFire == true){
        this.hideFire = false;
      }
      else if(this.hideFire == false){
        this.hideFire = true;
      }
    }
}

