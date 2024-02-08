class StringTask {


    getAbbreviate(days) {
        const abbreviateArray = new Array();
        for(let day of days) {
            abbreviateArray.push(day.trim().substr(0,3).toUpperCase())
        }
        return abbreviateArray;
    } 

    getCodedVersion(str) {
        return (str.trim().replace(/[aeiog]/g,(ch)=> {
            if(ch=='a') return '4'
            else if(ch=='e') return '3';
            else if(ch=='i') return '1';
            else if(ch=='o') return '0';
            else if(ch=='s') return '5';
        }));
    }
}



const taskObj = new StringTask();
console.log(taskObj.getAbbreviate(["Sunday","Monday "," Tuesday","Wednesday "," Thursday","Friday","Saturday"]));
console.log(taskObj.getCodedVersion("javascript is cool "));