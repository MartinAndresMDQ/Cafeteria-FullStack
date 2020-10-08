import { Component, Inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ClientService } from '@services';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { NewUserComponent } from './newUser';
import { Client, Operator } from '@model';

@Component({
  selector: 'users',
  styleUrls: ['./users.component.css'],
  templateUrl: './users.component.html'
})
export class UsersComponent implements OnInit {

  displayedColumns: string[] = ['id', 'level', 'name', 'password', 'online', 'available', 'edit', 'delete'];
  public dataSource = new MatTableDataSource([]);
  public title = 'Usuarios';
  public datos = [];

  constructor(@Inject(ClientService) private _clientService: ClientService,
    public dialog: MatDialog,
    private router: Router) {
  }

  ngOnInit(): void {
    this.TraerTodo();

  }

  TraerTodo() {
    this._clientService.getAlls().subscribe(
      data => {
        this.datos = data;
        this.dataSource = new MatTableDataSource(data);
      },
      error => {
        window.alert(error);
        console.error(<any>error)
      });
  }


  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  new() {
    const dialogRef = this.dialog.open(NewUserComponent, {
      width: '270px',
      data: { client: new Client(), isNew: true }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.TraerTodo();
      }
    });

  }

  edit(element) {
    const dialogRef = this.dialog.open(NewUserComponent, {
      width: '270px',
      data: { client: element, isNew: false }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result)
        this.TraerTodo();
    });

  }

  delete(element) {
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