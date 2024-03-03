export interface IRawRecipes{

    id: number;
    image: string;
    name: string;
    rating: number;
    cuisine: string;
    ingredients: string[];
    difficulty: string;
    instructions: string[];
    prepTimeMinutes: number;
    cookTimeMinutes: number;
    caloriesPerServing: number;
    servings: number;
    tags: string[];
    userId: number;
    reviewCount: number;
    mealType: string[];
}