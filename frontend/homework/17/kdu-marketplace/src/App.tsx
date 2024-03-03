import { Provider } from 'react-redux'
import './App.css'
import { KduMarketPlace } from './pages/KduMarketPlace/KduMarketPlace'
import { store } from './redux/store'


function App() {
  return (
    <Provider store={store}>
      <KduMarketPlace />
    </Provider>
  )

}

export default App
