const apiUrl: string = "https://dummyjson.com/recipes";


const callApiForQuery = async (query: string) => {

    let customUrl: string = apiUrl;

    if (query !== "") {
        customUrl = `${apiUrl}/search?q=${query}`
    }

    let response = await fetch(customUrl);
    let jsonResponse = await response.json();
    let data = jsonResponse.recipes;
    console.log("query: " + data.length);

    const divv = document.getElementById('cardsDivId');
    divv!.innerHTML = "";

    for (const element of data) {

        let currData = element;

        let time:number = parseInt(currData.prepTimeMinutes) + parseInt(currData.cookTimeMinutes);
        let ingredients: string[] = currData.ingredients;
        let rating: string = currData.rating;

        let content = `
            <div class="card">
                <div id="rating">
                    <p>${rating}</p>
                </div>
                <div class="img_div">
                    <img src="${currData.image}" alt="food">
                </div>
                <div class="name_div">
                    <p>${currData.name}</p>
                </div>
                <div class="ingredients_div">
                    <p>Ingredients</p>
                    <ul>
                        <li>${ingredients[0]}</li>
                        <li>${ingredients[1]}</li>
                        <li>${ingredients[2]}</li>
                        <li>${ingredients[3]}</li>
                    </ul>
                </div>
                <div class="details_div">
                    <div class="details">
                        <p>Cuisine: ${currData.cuisine}</p>
                    </div>
                    <div class="details">
                        <p>Difficulty: ${currData.difficulty}</p>
                    </div>
                    <div class="details">
                        <p>Total Time: ${time}</p>
                    </div>
                    <div class="details">
                        <p>${currData.caloriesPerServing} Calories</p>
                    </div>
                </div>
            </div>
    `;

    const divv = document.getElementById('cardsDivId');
    divv!.innerHTML += content;

    }



}

const generateDiv = async () => {

    let response = await fetch(apiUrl);
    let jsonResponse = await response.json();
    let data = jsonResponse.recipes;
    console.log(data.length);

    for (const element of data) {

        let currData = element;

        let time:number = parseInt(currData.prepTimeMinutes) + parseInt(currData.cookTimeMinutes);
        let ingredients: string[] = currData.ingredients;
        let rating: string = currData.rating;


        let content = `
            <div class="card">
                <div id="rating">
                    <p>${rating}</p>
                </div>
                <div class="img_div">
                    <img src="${currData.image}" alt="food">
                </div>
                <div class="name_div">
                    <p>${currData.name}</p>
                </div>
                <div class="ingredients_div">
                    <p>Ingredients</p>
                    <ul>
                        <li>${ingredients[0]}</li>
                        <li>${ingredients[1]}</li>
                        <li>${ingredients[2]}</li>
                        <li>${ingredients[3]}</li>
                    </ul>
                </div>
                <div class="details_div">
                    <div class="details">
                        <p>Cuisine: ${currData.cuisine}</p>
                    </div>
                    <div class="details">
                        <p>Difficulty: ${currData.difficulty}</p>
                    </div>
                    <div class="details">
                        <p>Total Time: ${time}</p>
                    </div>
                    <div class="details">
                        <p>${currData.caloriesPerServing} Calories</p>
                    </div>
                </div>
            </div>
    `;

    const divv = document.getElementById('cardsDivId');
    divv!.innerHTML += content;

    }
}

generateDiv();


const runQuery = ()=>{
    let inputValue = (<HTMLInputElement>document.getElementById('inputBox')).value;
    console.log(inputValue);
    callApiForQuery(inputValue);    
}