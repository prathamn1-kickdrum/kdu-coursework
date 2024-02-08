class Tip {
    constructor(bills) {
        this.bills=bills;
    }

    display(arr) {
        for(let price of arr) {
            console.log(price);
        }
    }

    tipCalculator() {
        const tips = new Array();
        const amounts = new Array();

        for(let price of this.bills) {
            let tip = 0;
            if(price<50) {
                tip = 0.2*price;
            }else if(price<200) {
                tip = 0.15*price;
            }else {
                tip = 0.1*price;
            }
            tips.push(tip);
            amounts.push(tip+price);
        }
        console.log("Tips : ")
        this.display(tips);
        console.log("Final Amounts : ")
        this.display(amounts);
    }

}
const tipObj = new Tip([40,45,280]);
tipObj.tipCalculator();