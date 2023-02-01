import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable()
export class FichaService {

  private idT = new BehaviorSubject(0);
  private user = new BehaviorSubject("default")
  currentId=this.idT.asObservable();
  currentUser=this.user.asObservable();

  constructor() { }

  changeId(ID:number){
    this.idT.next(ID)
  }
  changeUser(USER:string){
    this.user.next(USER)
  }
}
