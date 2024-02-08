class ObjectOperations {
  constructor(myObj) {
    this.myObj = myObj;
  }

  getAllKeys() {
    return Object.keys(this.myObj);
  }

  getAllValue() {
    const allValues = new Array();
    for (const [key, value] of Object.entries(this.myObj)) {
      allValues.push(value);
    }
    return allValues;
  }
}

const player = {
  firstName: "Leo",
  lastName: "Messi",
  address: {
    country: "Spain",
    city: "Barcelona",
  },
  careerInfo: {
    fcBarcelona: {
      appearances: 780,
      goals: {
        premierLeagueGoals: 590,
        championsLeagueGoals: 50,
      },
    },
  },
};

const objOp = new ObjectOperations(player);
console.log(objOp.getAllKeys());
console.log(objOp.getAllValue());
