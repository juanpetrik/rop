import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PeopleService } from '../service/people.service';
import { People } from '../model/people';
import { Contact } from '../model/contact';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-people-form',
  templateUrl: './people-form.component.html',
  styleUrls: ['./people-form.component.css']
})
export class PeopleFormComponent {
  people: People
  contact: Contact;
  contacts: Contact[] = [];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private peopleService: PeopleService) {
    this.people = new People();
    this.contact = new Contact();
  }

  onSubmit() {
    this.people.contacts = this.contacts;
    console.log(this.people);
    this.peopleService.save(this.people).subscribe(result => {
      if (result.id != null) {
        this.onSaveSuccess();
      } else {
        this.onSaveError();
      }
    });
  }

  protected onSaveSuccess(): void {
    alert("Cadastro realizado com sucesso!");
    this.people = new People();
    this.contact = new Contact();
    this.contacts = [];
  }

  protected onSaveError(): void {
    alert("Ocorreu um erro! Tente novamente.")
  }

  addContact() {
    if (this.contact.id == undefined || this.contact.id == null) {
      alert("Informe o ID do contato!");
      return;
    }

    if (this.contact.name == undefined || this.contact.name == null) {
      alert("Informe o Nome do contato!");
      return;
    }

    if (this.contact.phone == undefined || this.contact.phone == null) {
      alert("Informe o Telefone do contato!");
      return;
    }

    if (this.contact.email == undefined || this.contact.email == null) {
      alert("Informe o E-mail do contato!");
      return;
    }

    let contactPush = new Contact();
    contactPush.id = this.contact.id;
    contactPush.name = this.contact.name;
    contactPush.phone = this.contact.phone;
    contactPush.email = this.contact.email;

    console.log(this.contact);
    this.contacts.push(contactPush);

    this.contact = new Contact();
  }
}
