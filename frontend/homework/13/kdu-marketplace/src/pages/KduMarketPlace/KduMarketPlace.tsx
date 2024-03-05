import { BrowserRouter, Route, Routes } from 'react-router-dom'
import { HomePage } from '../HomePage/HomePage'
import { ProductPage } from '../ProductPage/ProductPage'
import { PageNotFound } from '../PageNotFound/PageNotFound'
import { Navbar } from '../../components/NavbarComponent/Navbar'
import { ProductsProvider } from '../../context/ProductsContext/ProductsProvider'

export const KduMarketPlace = () => {
    return (
        <BrowserRouter>
            <ProductsProvider>

                <Navbar />

                <Routes>
                    <Route path="/" element={<HomePage />} />
                    <Route path="/product/:uniqueId" element={<ProductPage />} />
                    <Route path="*" element={<PageNotFound />} />
                </Routes>

            </ProductsProvider>
        </BrowserRouter>
    )
}
