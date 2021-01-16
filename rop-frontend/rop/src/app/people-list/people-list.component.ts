import { Component, OnInit } from '@angular/core';
import { People } from '../model/people';
import { PeopleService } from '../service/people.service';

@Component({
  selector: 'app-people-list',
  templateUrl: './people-list.component.html',
  styleUrls: ['./people-list.component.css']
})
export class PeopleListComponent implements OnInit {

  peoples: any[];
  page: number = 0;
  size: number = 100;
  config: any;

  constructor(private peopleService: PeopleService) {
    this.config = {
      itemsPerPage: 5,
      currentPage: 1,
      totalItems: 0
    }
  }

  ngOnInit() {
    this.peopleService.findAll("", this.page, this.size).subscribe(data => {
      this.peoples = data.content;
      console.log(this.peoples);
      this.config.totalItems = this.peoples.length;
    });
  }

  pageChanged(event){
    this.config.currentPage = event;
  }

}
