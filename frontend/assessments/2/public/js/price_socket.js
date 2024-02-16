const socket = io("http://127.0.0.1:5000/");

let graphContainer = document.getElementsByClassName("graph-container")[0];
let priceValue = document.getElementById("price-value");
let priceChange = document.getElementById("price-change");
let historyContainer = document.getElementsByClassName(
  "history-item-container"
)[0];
const maxPriceLimit = 5000;
const scaleFactor = maxPriceLimit > 0 ? 600 / maxPriceLimit : 1;
const GREEN = "#2F9E44";
const RED = "#e03131";
let newPrice = 0;

document.addEventListener("DOMContentLoaded", async () => {
  try {
    const response = await fetch("http://127.0.0.1:5000/api/v1/zomato");
    const data = await response.json();
    document.getElementById("company-name").innerText = data.company.name;
    priceValue.innerText = data.company.price;
    newPrice = data.company.price;
    document
      .getElementById("company-logo")
      .setAttribute("src", data.company.logo);
    fetchHistoryData();
  } catch (error) {
    console.error("Error fetching data:", error);
  }
});

const fetchHistoryData = async () => {
  const response = await fetch("http://127.0.0.1:5000/api/v1/history/zomato");
  const data = await response.json();
  console.log(data);
  console.log(data.company.transactionHistory);
  historyContainer.innerHTML = "";
  data.company.transactionHistory.forEach((element) => {
    insertHistoryItem(element);
  });
};

const insertHistoryItem = (element) => {
  const stocks = element.stocks;
  const action = element.type;
  const date = element.date;
  const price = element.price;
  const historyItem = document.createElement("div");
  // Create the HTML string
  const historyItemHtml = `
    <div class="history-item">
        <div>
            <h3>${stocks} stocks - $${price} </h3>
            <p>${date}</p>
        </div>
        <div>
            <p>${action}</p>
        </div>
    </div>
`;
  historyItem.innerHTML = historyItemHtml;
  historyContainer.appendChild(historyItem);
};

const makeTransaction = async (type) => {
  const options = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      type: type,
      stocks: parseInt(document.getElementById("qty").value),
      price: parseFloat(priceValue.innerText),
      date: new Date().toGMTString(),
    }),
  };
  const response = await fetch(
    "http://127.0.0.1:5000/api/v1/transaction/zomato",
    options
  );
  const data = await response.json();
  document.getElementById("qty").value = "";
  fetchHistoryData();
};

document.getElementById("buy-button").addEventListener("click", () => {
  makeTransaction("buy");
});
document.getElementById("sell-button").addEventListener("click", () => {
  makeTransaction("sell");
});

const createGraphColumn = (prevPrice, newPrice) => {
  let priceDiv = document.createElement("div");
  priceDiv.style.height = newPrice * scaleFactor + "px";
  priceDiv.classList.add("graph-column");
  if (prevPrice < newPrice) {
    priceChange.style.color = GREEN;
    priceValue.style.color = GREEN;
    priceDiv.classList.add("green-line");
  } else {
    priceChange.style.color = RED;
    priceValue.style.color = RED;
    priceDiv.classList.add("red-line");
  }
  priceValue.innerText = newPrice;
  priceChange.innerText = (newPrice - prevPrice) / 100 + " %";
  graphContainer.appendChild(priceDiv);
};

socket.on("serverMessage", (msg) => {
  console.log(msg);
  let prevPrice = newPrice;
  newPrice = msg;
  createGraphColumn(prevPrice, newPrice);
});
