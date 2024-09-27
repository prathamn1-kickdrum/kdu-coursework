import { IRawRecipes } from "../interfaces/IRawRecipes";
import { IRecipes } from "../interfaces/IRecipes";

export const mapRawToPreety = (recipe: IRawRecipes): IRecipes => {

    return {
        image: recipe.image,
        name: recipe.name,
        rating: recipe.rating,
        cuisine: recipe.cuisine,
        ingredients: recipe.ingredients,
        difficulty: recipe.difficulty,
        instructions: recipe.instructions,
        time_taken: recipe.prepTimeMinutes + recipe.cookTimeMinutes,
        caloriesCount: recipe.caloriesPerServing,
    }
}