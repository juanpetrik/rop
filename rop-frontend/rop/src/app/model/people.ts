import { Contact } from './contact';

export class People {
  id: number;
  name: string;
  cpf: string;
  dateOfBirth: Date;
  contacts: Contact[];
}
