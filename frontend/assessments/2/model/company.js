class Company {
  constructor(name, base_price, logo) {
    this.name = name;
    this.base_price = base_price;
    this.logo = logo;
    this.transaction_history = [];
    this.price_history = [];
  }

  getTransactionHistory() {
    return this.transaction_history;
  }

  getPrice() {
    return this.base_price;
  }

  getLogo() {
    return this.logo;
  }

  getName() {
    return this.name;
  }

  addPrice(price) {
    this.price_history.push(price);
  }

  addTransaction(type, stocks, price,date) {
    this.transaction_history.push({
      type,
      stocks,
      price,
      date,
    });
  }
}

module.exports = Company;
