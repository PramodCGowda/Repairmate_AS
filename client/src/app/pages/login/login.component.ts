import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, HttpClientModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  loginObj: Login;

  constructor(private httpClient: HttpClient, private router: Router) {
    this.loginObj = new Login();
  }

  signup(): void {
    this.router.navigateByUrl('/singup');
  }

  onLogin() {
    this.httpClient.post('http://localhost:8080/login', this.loginObj).subscribe((res:any) => {
      if(res.result) {
        console.log(res.message);
        this.router.navigateByUrl('/dashboard');
      } else {
        console.log(res.message);
      }
    })
  }
}


export class Login {
  email: string;
  password: string;

  constructor() {
    this.email = '';
    this.password = '';
  }
}