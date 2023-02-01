import { Injectable } from '@angular/core';
import { Game } from './Game';
import { Observable } from 'rxjs';
import { of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs';



@Injectable({
  providedIn: 'root'
})
export class PartidaService {
  
private urlEndPoint:string ='http://localhost:8080/game'


private httpHeaders = new HttpHeaders({'Content-Type':'application/json'})

  constructor(private http: HttpClient) { }

  getPartidas(): Observable<Game[]>{
    return this.http.get<Game[]>('http://localhost:8080/game/all');
  }

  create(game: Game):Observable<Game>{
    return this.http.post<Game>(this.urlEndPoint,game)
  }


  getPartida(id: Number): Observable<Game>{
    return this.http.get<Game>(`${this.urlEndPoint}/${id}`)
  }


  delete(id: number):Observable<Game>{
    return this.http.delete<Game>(`${this.urlEndPoint}/${id}`)
  }
}
