import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.tsx'
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import ErrorPage from './components/ErrorPage.tsx';
import StockPriceGraph from './components/StockPriceGraph.tsx';
import Dashboard  from './components/Dashboard.tsx';
import TransactionList from './components/TransactionList.tsx';
import StockSummarize from './components/StockSummarize.tsx';
import { Provider } from 'react-redux';
import { store } from './redux/store/store.ts';

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    errorElement: <ErrorPage />,
    children: [
      {
        path: "/",
        element: <Dashboard />
      },
      {
        path: "/details/:code",
        element: <StockPriceGraph />
      },
      {
        path: "/portfolio",
        element: <TransactionList />
      },
      {
        path: "/summarizer",
        element: <StockSummarize />
      },
    ]
  },
]);

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <Provider store={store}>
      <RouterProvider router={router} />
    </Provider>
  </React.StrictMode>,
)
