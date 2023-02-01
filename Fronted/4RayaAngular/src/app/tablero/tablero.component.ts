import { Component } from '@angular/core';
import { ColumnaComponent } from '../columna/columna.component';


@Component({
  selector: 'app-tablero',
  templateUrl: './tablero.component.html',
  styleUrls: ['./tablero.component.css']
})
export class TableroComponent {
  
    columnas= [0,1,2,3,4,5,6]

      columna!: ColumnaComponent;

    constructor(){}

    clickColumn(columna:number){

      console.log(columna);
      
    }
  
}

