import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { People } from '../model/people';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class PeopleService {

  private peopleUrl: string;
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Methods': 'GET, POST, OPTIONS, PUT, PATCH, DELETE',
      'Access-Control-Allow-Headers':
        'Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers'
    })
  };

  constructor(private http: HttpClient) {
    this.peopleUrl = 'http://localhost:8080/api/people';
  }

  public findAll(search: string, page: number, size: number): Observable<People[]> {
    return this.http.get<People[]>(this.peopleUrl + '?search=' + search + '&page=' + page + '&size=' + size);
  }

  public save(people: People) {
    return this.http.post<People>(this.peopleUrl, people, this.httpOptions);
  }
}
