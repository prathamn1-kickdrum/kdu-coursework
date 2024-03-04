import { Provider } from 'react-redux';
import { store } from './redux/Store';
import HotelBookingMain from './components/HotelBookingMain/HotelBookingMain';
import './App.scss'

function App() {

  return (
    <Provider store={store} >
        <HotelBookingMain/>
    </Provider>  
  )
}

export default App
