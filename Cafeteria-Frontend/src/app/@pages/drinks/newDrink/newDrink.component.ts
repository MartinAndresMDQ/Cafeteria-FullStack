import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

import { Drink } from '@model';
import { AdditionalService, DrinkService } from '@services';

@Component({
  selector: 'newDrink',
  templateUrl: './newDrink.component.html'
})
export class NewDrinkComponent {

  constructor(@Inject(DrinkService) private _drinkService: DrinkService,
    @Inject(AdditionalService) private _additionalService: AdditionalService,
    public dialogRef: MatDialogRef<NewDrinkComponent>,
    @Inject(MAT_DIALOG_DATA) public data: {drink:Drink,isDrink:Boolean}) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

  save(){
    if(!this.data.isDrink){
      
    this._additionalService.save(this.data.drink).subscribe(
      data => {
        this.dialogRef.close(data);
      },
      error => {
        window.alert(error);
        console.error(<any>error)
      });
    }
    else {
      this._drinkService.save(this.data.drink).subscribe(
        data => {
          this.dialogRef.close(data);
        },
        error => {
          window.alert(error);
          console.error(<any>error)
        });
    }
    
  }

}