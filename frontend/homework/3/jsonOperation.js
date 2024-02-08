class JsonOperation {
    constructor(str) {
        this.str= str;
        this.jsonStr=null;
    }

    convertToJson() {
        this.jsonStr=JSON.parse(this.str,(key,value) => {
            if( typeof value ==='string') {
                return value.toUpperCase();
            }
            return value;
        })
        return this.jsonStr;
    }

    getJsonString() {
        return this.jsonStr;
    }

    deleteKeys(keysToDelete) {
        keysToDelete.forEach(element => {
            delete this.jsonStr[element];
        });
    }

    getJavaScriptString() {
        return  JSON.stringify(this.jsonStr);
    }

}


const jsonObj = new JsonOperation(
    '{"firstName":"Alex","lastName":"Hunter","email":"alex@yahoo.com","age":24, "city":"london", "country":"england"}'
    );

console.log(jsonObj.convertToJson());
jsonObj.deleteKeys(['email']);
console.log(jsonObj.getJavaScriptString());
