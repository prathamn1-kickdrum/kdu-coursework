import { callApiAndGetPromise, callApiAndReturnJson, callApiAndSaveData, callApiForQuery } from "./apis/apiHandler";

export class RecipeHandler{

    fetchRecipesFromAPI(){
        callApiAndGetPromise();
    }

    searchRecipes(query: string){
        callApiForQuery(query);
    }

    printAllRecipes(){
        callApiAndSaveData();
    }

    getData(query: string){
        callApiAndReturnJson(query);
    }
}

const recipeHandler: RecipeHandler = new RecipeHandler();
recipeHandler.printAllRecipes();
recipeHandler.fetchRecipesFromAPI();
recipeHandler.searchRecipes("pasta");