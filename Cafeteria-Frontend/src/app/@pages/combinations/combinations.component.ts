import {Component, Inject, OnInit} from '@angular/core';
// import { Router } from '@angular/router';
import { AdditionalService, DrinkService, CombinationService } from '@services';
import {MatTableDataSource} from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { NewCombinationComponent } from './newCombination';
import { Combination } from '@model';

@Component({
  selector: 'combinations',
  styleUrls: ['./combinations.component.css'],
  templateUrl: './combinations.component.html'
})
export class CombinationsComponent implements OnInit {

  displayedColumns: string[] = ['id', 'fecha', 'name', 'edit','descripcion','total'];
  public dataSource = new MatTableDataSource([]);
  public title = '';
  // public isDrink = true;
  public datos = [];

  constructor(@Inject(CombinationService) private _combinationService: CombinationService,
  // @Inject(DrinkService) private _drinkService: DrinkService,
  // @Inject(AdditionalService) private _additionalService: AdditionalService,
  public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.TraerTodo();

  }

  TraerTodo(){
      this.title = 'Pedidos';
      this._combinationService.getAlls().subscribe(
        data => {
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

    const dialogRef = this.dialog.open(NewCombinationComponent, {
      width: '250px',
      data: { combination:new Combination(), view:false }
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
    const dialogRef = this.dialog.open(NewCombinationComponent, {
      width: '250px',
      data: {combination:element, view:true}
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result)
        this.TraerTodo();
    });
    
  }

  delete(element){    
    this._combinationService.delete(element.id).subscribe(
      data => {
        this.TraerTodo();
      },
      error => {
        window.alert(error);
        console.error(<any>error)
      });
  }
}