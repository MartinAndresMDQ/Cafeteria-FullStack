import {Component, Inject, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { AdditionalService, DrinkService } from '@services';
import {MatTableDataSource} from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { NewDrinkComponent } from './newDrink';
import { Additional, Drink } from '@model';

@Component({
  selector: 'drinks',
  styleUrls: ['./drinks.component.css'],
  templateUrl: './drinks.component.html'
})
export class DrinksComponent implements OnInit {

  displayedColumns: string[] = ['id', 'name', 'price', 'edit', 'delete'];
  public dataSource = new MatTableDataSource([]);
  public title = '';
  public isDrink = true;
  public datos = [];

  constructor(@Inject(DrinkService) private _drinkService: DrinkService,
  @Inject(AdditionalService) private _additionalService: AdditionalService,
  public dialog: MatDialog,
    private router: Router) {
  }

  ngOnInit(): void {
    this.TraerTodo();

  }

  TraerTodo(){
      
    if (this.router.url.search("additionals") > 0){
      this.title = 'Adicionales';
      this.isDrink = false;
    this._additionalService.getAlls().subscribe(
      data => {
        this.datos = data;
        this.dataSource = new MatTableDataSource(data);
      },
      error => {
        window.alert(error);
        console.error(<any>error)
      });
    }
    else {
      this.title = 'Bebidas';
      this._drinkService.getAlls().subscribe(
        data => {
          this.datos = data;
          this.dataSource = new MatTableDataSource(data);
        },
        error => {
          window.alert(error);
          console.error(<any>error)
        });
    }
  }

  public data

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  new(){

    const dialogRef = this.dialog.open(NewDrinkComponent, {
      width: '250px',
      data: { drink:this.isDrink?new Drink():new Additional(), isDrink:this.isDrink }
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result)
      {
        this.datos.push(result);
        this.dataSource = new MatTableDataSource(this.datos);
      }
    });
    
  }

  edit(element){
    
    const dialogRef = this.dialog.open(NewDrinkComponent, {
      width: '250px',
      data: {drink:element,isDrink:this.isDrink}
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result)
        this.TraerTodo();
    });
    
  }

  delete(element){    
    this._drinkService.delete(element.id).subscribe(
      data => {
        this.TraerTodo();
      },
      error => {
        window.alert(error);
        console.error(<any>error)
      });
  }
}