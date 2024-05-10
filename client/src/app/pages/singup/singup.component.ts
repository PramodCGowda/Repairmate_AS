import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-singup',
  standalone: true,
  imports: [FormsModule, HttpClientModule],
  templateUrl: './singup.component.html',
  styleUrl: './singup.component.scss'
})
export class SingupComponent {

    singupObj: Singup;

    constructor(private httpClient: HttpClient, private router:Router) {
      this.singupObj = new Singup();
    }

    onCreate() {
      this.httpClient.post('http://localhost:8080/signup', this.singupObj).subscribe((res:any) => {
        if(res.message) {
          console.log(res.message);
          this.router.navigateByUrl('/login');
        } else {
          console.log(res.message);
        }
      })
    }
}

export class Singup {
  username: String;
  password: String;
  email: String;

  constructor() {
    this.username = '';
    this.email = '';
    this.password = '';
  }
}
