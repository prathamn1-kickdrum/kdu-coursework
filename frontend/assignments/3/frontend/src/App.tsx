import { ThemeProvider as CustomThemeProvider, createGlobalStyle } from 'styled-components'
import { appTheme } from './utils/theme'
import Navigation from './components/Navigation'
import { Outlet } from 'react-router-dom'
import { ThemeProvider, createTheme } from "@mui/material/styles";
import { theme as CustomTheme } from './utils/colors';
import { useEffect } from 'react';
import { store } from './redux/store/store';
import { LocalizationProvider } from '@mui/x-date-pickers';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs'
import { fetchStocks } from './redux/slice/ApiStocksSlice';
import { fetchPortfolio } from './redux/slice/UserStockSlice';


const GlobalStyle = createGlobalStyle`
  * {
    padding: 0;
    margin: 0;
    box-sizing: border-box;
    text-decoration: none;
    font-weight: normal;
    font-family: "Poppins", sans-serif;
  }

  input {
    border: none;
    outline: none;
    background-color: transparent;
  }

  button {
    border: none;
    outline: none;
    background-color: transparent;
  }
`

function App() {
  const theme = createTheme(
    {
      components: {
        MuiTabs: {
          styleOverrides: {
            indicator: {
              borderBottom: `2px solid ${CustomTheme.primaryColor}`, 
            },
          },
        }
      },
      palette: {
        primary: {
          main: CustomTheme.primaryColor
        },
        secondary: {
          main: CustomTheme.greyPrimary
        }
      }
    }
  );

  useEffect(() => {
    store.dispatch(fetchStocks())
    store.dispatch(fetchPortfolio());
  }, [])

  return (
    <LocalizationProvider dateAdapter={AdapterDayjs}>
      <ThemeProvider theme={theme}>
        <CustomThemeProvider theme={appTheme}>
          <GlobalStyle />
          <Navigation />
          <Outlet />
        </CustomThemeProvider>
      </ThemeProvider>
    </LocalizationProvider>
  )
}

export default App