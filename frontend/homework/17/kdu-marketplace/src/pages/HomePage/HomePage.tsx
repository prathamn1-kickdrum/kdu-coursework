import Footer from '../../components/Footer/Footer.tsx'
import { ProductList } from '../../components/ProductListComponent/ProductList.tsx'
import { heading1, homepage, blueSpan } from './HomePage.style.ts'

export const HomePage:React.FC = () => {
    return (
        <div className='homepage' style={homepage}>
            <h1 style={heading1}>
                KDU <span style={blueSpan}>MARKETPLACE</span>
            </h1>
            <ProductList />
            <Footer/>
        </div>
    )
}
