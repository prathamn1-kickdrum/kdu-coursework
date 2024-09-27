import { Rawdb, db } from "../database/db";
import { IRawRecipes } from "../interfaces/IRawRecipes";
import { mapRawToPreety } from "./Mapper";
import { IRecipes } from "../interfaces/IRecipes";

const axios = require('axios');

const apiUrl: string = "https://dummyjson.com/recipes";

export const callApiAndSaveData = ()=>{

    const options = {
        method: 'GET',
		url: apiUrl,
    }

    let data: IRawRecipes[] = [];

    axios.request(options)
    .then((response: { data: { recipes: IRecipes; }; })=>{

        const allRecipes: any = response.data.recipes;
        data = [...allRecipes];

    })
    .then(()=>{

        data.forEach((rawRecipe)=>{
            Rawdb.push(rawRecipe);
            db.push(mapRawToPreety(rawRecipe));
        })

        console.log(db);
    })

}

export const callApiAndReturnJson = async(query: string)=>{

    let customUrl: string = apiUrl;

    if(query !== ""){
        customUrl =  `${apiUrl}/search?q=${query}`
    }

    const options = {
        method: 'GET',
		url: customUrl,
    }

    let response =  await axios.request(options);
    
    return response.data.recipes;

}

export const callApiForQuery = (query: string)=>{

    const options = {
        method: 'GET',
		url: `${apiUrl}/search?q=${query}`,
    }

    axios.request(options)
    .then((response: { data: { recipes: IRecipes; }; })=>{

        const allRecipes: any = response.data.recipes;
        console.log(allRecipes);

    })   

}

export const callApiAndGetPromise = ()=>{

    const options = {
        method: 'GET',
		url: apiUrl,
    }

    console.log(axios.request(options));    

}