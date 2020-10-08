import { Component, OnInit, Inject, ChangeDetectionStrategy } from '@angular/core';

import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';

import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

import { Additional, Combination, Drink } from '@model';
import { AdditionalService, CombinationService, DrinkService } from '@services';
// import { i18nMetaToJSDoc } from '@angular/compiler/src/render3/view/i18n/meta';

@Component({
  selector: 'newCombination',
  styleUrls: ['./newCombination.component.css'],
  templateUrl: './newCombination.component.html',
  // changeDetection: ChangeDetectionStrategy.OnPush
})
export class NewCombinationComponent implements OnInit {
  myControlDrink = new FormControl();
  optionsDrinks: Drink[] = [];
  filteredOptionsDrinks: Observable<string[]>;

  myControlAdditional = new FormControl();
  optionsAdditionals: Additional[] = [];
  filteredOptionsAdditionals: Observable<string[]>;

  ngOnInit() {
    if(!this.data.view){
      this.traerDrinks();
      this.traerAdditional();
    }
    // else{
    //   this.optionsDrinks = [this.data.combination.drink];
    //   this.filteredOptionsDrinks = this.myControlDrink.valueChanges.pipe(
    //     startWith(''),
    //     map(value => this._filterDrink(value))
    //   );
    //   this.optionsAdditionals = this.data.combination.additionals;
    //   this.filteredOptionsAdditionals = this.myControlAdditional.valueChanges.pipe(
    //     startWith(''),
    //     map(value => this._filterAdditional(value))
    //   );

    // }
  }

  private _filterDrink(value): any[] {
    // console.log(value)
    let filterValue = "";
    if(value?.name)
    filterValue = value.name.toLowerCase();
    else if(value)
    filterValue = value.toLowerCase();
    return this.optionsDrinks.filter(option => option.name.toLowerCase().indexOf(filterValue) === 0);
  }

  private _filterAdditional(value): any[] {
    // console.log(value)
    let filterValue = "";
    if(value?.name)
    filterValue = value.name.toLowerCase();
    else if(value)
    filterValue = value.toLowerCase();
    return this.optionsAdditionals.filter(option => option.name.toLowerCase().indexOf(filterValue) === 0);
  }

  constructor(
    @Inject(CombinationService) private _combinationService: CombinationService,
  @Inject(DrinkService) private _drinkService: DrinkService,
    @Inject(AdditionalService) private _additionalService: AdditionalService,
    public dialogRef: MatDialogRef<NewCombinationComponent>,
    @Inject(MAT_DIALOG_DATA) public data: {combination:Combination,view:Boolean}) {}

  onNoClick(): void {
    this.dialogRef.close(this.data.combination);
  }

  traerDrinks(){
    this._drinkService.getAlls().subscribe(
      data => {
        
        this.optionsDrinks = data;
        this.filteredOptionsDrinks = this.myControlDrink.valueChanges.pipe(
          startWith(''),
          map(value => this._filterDrink(value))
        );
      },
      error => {
        window.alert(error);
        console.error(<any>error)
      });  
  }

  traerAdditional(){
    
    this._additionalService.getAlls().subscribe(
      data => {
        this.optionsAdditionals = data;
        // this.optionsDrinks = data;
        this.filteredOptionsAdditionals = this.myControlAdditional.valueChanges.pipe(
          startWith(''),
          map(value => this._filterAdditional(value))
        );
      },
      error => {
        window.alert(error);
        console.error(<any>error)
      });  
  }

  save(){
    this._combinationService.save(this.data.combination).subscribe(
      data => {
        this.data.combination = data;
        // this.dialogRef.close(data);
        this.data.view = true;
      },
      error => {
        window.alert(error);
        console.error(<any>error)
      });    
  }

  addAdditional(){
    this.data.combination.additionals.push(new Additional());
  }

  
  getOptionText(option) {
    return option?.name;
  }

  selectAdditional(i:number,data:Additional){
    this.data.combination.additionals[i]=data;
  }

  selectDrink(data:Drink){
    this.data.combination.drink=data;
  }


  isDisabled(){
    let paso = false;

    if(this.data.combination.drink.id==0)
      paso = true;
      else
    for(let i=0; i<this.data.combination.additionals.length;i++){
      if(this.data.combination.additionals[i].id==0){
        paso = true;
        i=this.data.combination.additionals.length;
      }
    }
    return paso;
  }
}