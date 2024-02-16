export interface IRecipe {
    Image : string
    Name: string
    Rating: number
    Cuising: string
    Ingredients: Array<string>
    Difficulty: string
    TimeTaken: number
    CalorieCount: number
}

class RecipeRepo {
    recipeList: Array<IRecipe>;
  
    constructor() {
      this.recipeList = [];
    }

    addRecipe(myRecipe : IRecipe | IRecipe[]) : void {
        if(Array.isArray(myRecipe)) {
            this.recipeList.push(...myRecipe);
        }else {
            this.recipeList.push(myRecipe);
        }
    }

    getAllRecipe() {
        return this.recipeList;
    }


  }