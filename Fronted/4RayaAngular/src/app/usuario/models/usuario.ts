export class Usuario {
    private userName: string;
    private password: string;

    constructor(userName: string, password: string){
        this.userName = userName;
        this.password = password;
    }

    public setUserName(userName: string){
        this.userName = userName;
    }

    public getUserName(){
        return this.userName;
    }

    public setPassword(password: string){
        this.password = password;
    }

    public getPassword(){
        return this.password;
    }
}
