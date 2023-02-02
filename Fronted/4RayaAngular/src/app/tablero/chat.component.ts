import { Usuario } from './../usuario/models/usuario';
import { PartidaComponent } from './../partida/partida.component';
import { Game } from './../game';
import { TableroComponent } from './tablero.component';

//dependencias necesarias para la aplicacion
import { Mensaje } from './models/mensaje';
import { Component, Input } from '@angular/core';
//esta dependencias se instalan con npm install @stomp/stompjs@5.2.0 *importante esta version porque la ultima da problemas
import { Client } from '@stomp/stompjs'
import * as SockJS from 'sockjs-client';




@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent {

//variables creadas y necesarias para el desarrollo

  private client!: Client;
  conectado:boolean =false;
  mensaje:Mensaje = new Mensaje();
  mensajes:Mensaje[] =[];
  escribiendo!: string;
  clienteId!: string;

  @Input() userName:string;
  

  //constructor generando la variable clienteId
  constructor(){
    this.clienteId = 'id-' + new Date().getTime() + '-' + Math.random().toString(36).substr(2);
  }


  //metodo OnInit que se ejecuta al lanzar la aplicacion

  ngOnInit(): void {

    //se crea un cliente

    this.client=new Client;
  
    this.mensaje.user=this.userName;
    //aqui creamos la conexion con el backend que activaremos mas adelante una vez que pulsemos el boton de conectar y llamara a la funcion  conectar() desde html

    this.client.webSocketFactory=() =>{
      return new SockJS("http://localhost:8080/chat-websocket");
   };
  
   


    this.client.onConnect = (frame) => {
      //se saca por consola del navegador el mensaje que estamos conectados
      console.log('Conectados :' + this.client.connected + ' : ' + frame)
      this.conectado=true;
      
      this.client.subscribe('/chat/mensaje', e => {
        //mandamos el mensaje al backend
        let mensaje: Mensaje = JSON.parse(e.body) as Mensaje;
        mensaje.fecha = new Date(mensaje.fecha);


        // se da un color a cada usuario el color se genera el back con un random
        if (!this.mensaje.color && mensaje.tipo == 'NUEVO_USUARIO' &&
        this.mensaje.user == mensaje.user) {
        this.mensaje.color = mensaje.color;
      }

        this.mensajes.push(mensaje);



        //se saca por consola el mensaje
        console.log(mensaje);
      });

      // si el usuario esta escribiendo se mostrara en la pantalla
      this.client.subscribe('/chat/escribiendo', e => {
        this.escribiendo = e.body;
        setTimeout(() => this.escribiendo = '', 3000)

      });


      console.log(this.clienteId);
      this.client.subscribe('/chat/historial/' + this.clienteId, e => {
        const historial = JSON.parse(e.body) as Mensaje[];
        this.mensajes = historial.map(m => {
          m.fecha = new Date(m.fecha);
          return m;
        }).reverse();
        this.client.publish({ destination: '/app/historial', body: this.clienteId });
      });

      

      this.mensaje.tipo = 'NUEVO_USUARIO';
      this.client.publish({ destination: '/app/mensaje', body: JSON.stringify(this.mensaje) });
    }
    


//una vez que pulsemos el boton desconectar se desconectara del backend
    this.client.onDisconnect = (frame) => {
      console.log('Desonectados :' + !this.client.connected + ' : ' + frame)
      this.conectado=false;
      this.mensaje = new Mensaje();
      this.mensaje.user=this.userName;
      this.mensajes = [];
    }  
   
   
  }

  //una vez que pulsemos el boton conectar se activara la conexion
  conectar():void{
    this.client.activate();
  }
//una vez que pulsemos el boton desconetar se quitara la conexion
  desconectar():void{
    this.mensaje.user=this.userName;
    this.client.deactivate();
  }
  
//cuando pulsemos el boton de enviar mensaje se mandara el mensaje
  enviarMensaje(): void {
    this.mensaje.tipo = 'MENSAJE';
    this.client.publish({ destination: '/app/mensaje', body: JSON.stringify(this.mensaje) });
    this.mensaje.texto = '';
  }
  
  //&¡cuando estemos escribiendo se mandara el mensaje
  escribiendoEvento(): void {
    this.client.publish({ destination: '/app/escribiendo', body: this.mensaje.user });
  }
 
}
