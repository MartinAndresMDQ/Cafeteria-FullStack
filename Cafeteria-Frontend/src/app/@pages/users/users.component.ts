import {Component, Inject} from '@angular/core';
import { Router } from '@angular/router';
import { ClientService } from '@services';
import {MatTableDataSource} from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { NewUserComponent } from './newUser';
import { Client, Operator } from '@model';

@Component({
  selector: 'users',
  styleUrls: ['./users.component.css'],
  templateUrl: './users.component.html'
})
export class UsersComponent {

  displayedColumns: string[] = ['id', 'name', 'password', 'online', 'edit', 'delete'];
  public dataSource = new MatTableDataSource([]);
  public title = 'Usuarios';
  // public isUser = true;
  public datos = [];

  constructor(@Inject(ClientService) private _clientService: ClientService,
  public dialog: MatDialog,
    private router: Router) {
  }

  ngOnInit(): void {
    this.TraerTodo();

  }

  TraerTodo(){

      // this.title = 'Adicionales';
      // this.isUser = false;
    this._clientService.getAlls().subscribe(
      data => {
        console.log(data);
        this.datos = data;
        this.dataSource = new MatTableDataSource(data);
      },
      error => {
        window.alert(error);
        console.error(<any>error)
      });
  }

  public data

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  new(){

    const dialogRef = this.dialog.open(NewUserComponent, {
      width: '250px',
      data: { client:new Client(),isNew:true }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      if(result)
      {
        this.datos.push(result);
        this.dataSource = new MatTableDataSource(this.datos);
      }
    });
    
  }

  edit(element){
    const dialogRef = this.dialog.open(NewUserComponent, {
      width: '250px',
      data: { client:element,isNew:false }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      if(result)
        this.TraerTodo();
    });
    
  }

  delete(element){
    console.log(element)
    this._clientService.delete(element.id).subscribe(
      data => {
        this.TraerTodo();
      },
      error => {
        window.alert(error);
        console.error(<any>error)
      });
  }
}