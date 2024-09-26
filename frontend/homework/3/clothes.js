class Warehouse {
    constructor() {
        this.items = new Array();
    }

    addItem(clothes) {
        this.items.push(...clothes);
    }

    getTotalPrice() {
        let totalPrice = 0;
        for(let item of this.items) {
            totalPrice+=item.price;
        }
        return totalPrice;
    }

    sortByPrice() {
        this.items.sort((a,b)=>b.price-a.price);
    }

    getBlueClothes() {
        const blueClothes=new Array();
        for(let item of this.items) {
            if(item.isBlue()) {
                blueClothes.push(item);
            }
        }
        return blueClothes;
    }


}

class Cloth{
    constructor(type,color,size,price) {
        this.type=type;
        this.color=color;
        this.size=size;
        this.price=price;
    }

    isBlue() {
        return this.color.toLowerCase()==='blue';
    }
}

class Shirt extends Cloth {
    constructor(color,size,price) {
        super('SHIRT',color,size,price);
    }
}

class Shoe extends Cloth {
    constructor(color,size,price) {
        super('SHOE',color,size,price);
    }
}


const warehouseObj = new Warehouse();
const shirtList = [new Shirt('black','XL',3000),new Shirt('blue','X',2000), new Shirt('red','M',1500)]
const shoeList = [new Shirt('black','6',1500),new Shirt('blue','7',2050), new Shirt('red','5',1000)]

warehouseObj.addItem(shirtList);
warehouseObj.addItem(shoeList);

console.log(warehouseObj.getTotalPrice());

warehouseObj.sortByPrice();
console.log(warehouseObj.items);

console.log(warehouseObj.getBlueClothes());
