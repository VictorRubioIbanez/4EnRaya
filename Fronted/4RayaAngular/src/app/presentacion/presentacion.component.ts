import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-presentacion',
  templateUrl: './presentacion.component.html',
  styleUrls: ['./presentacion.component.css']
})
export class PresentacionComponent {

  constructor(private router:Router){}
  direccion:string='/assets/imagenes/image.png'

ngOnInit(): void {


  }


}
