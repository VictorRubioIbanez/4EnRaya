
import { Subscription } from 'rxjs';
import { Component, Input } from '@angular/core';
import {  getNgModuleById } from '@angular/core';
import { FichaService } from './../ficha.service';


@Component({
  selector: 'app-tablero',
  templateUrl: './tablero.component.html',
  styleUrls: ['./tablero.component.css']
})
export class TableroComponent {
  
    columnas= [0,1,2,3,4,5,6]



    idT:number;

    suscription:Subscription;

    constructor(private data:FichaService){

    }


    mov :number;

    ngOnInit(): void {
      this.suscription=this.data.currentId.subscribe(idT => this.idT=idT)
     

    }
  

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

