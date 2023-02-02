export class Datos{
    
    idGame:string;

    idPlayer:string;

    colum:number;

    turn:number;

    constructor(idGame:string,player:string,colum:number,turn:number){
            this.idGame=idGame;
            this.idPlayer=player;
            this.colum=colum;
            this.turn=turn;
    }
}