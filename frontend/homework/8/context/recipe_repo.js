"use strict";
const { IRecipe } = require('../models/recipe');
class RecipeRepo {
    constructor() {
        this.recipeList = [];
    }
    addRecipe(myRecipe) {
        if (Array.isArray(myRecipe)) {
            this.recipeList.push(...myRecipe);
        }
        else {
            this.recipeList.push(myRecipe);
        }
    }
    getAllRecipe() {
        return this.recipeList;
    }
}
