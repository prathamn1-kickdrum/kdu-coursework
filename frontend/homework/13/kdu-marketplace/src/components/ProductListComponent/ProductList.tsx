import { useContext } from 'react'
import { ProductsContext } from '../../context/ProductsContext/ProductsContext.tsx'
import { ProductItem } from '../ProductItemComponent/ProductItem.tsx';
import { ProductItemContext } from '../../context/ProductItemContext/ProductItemContext.tsx';
import { productListBox } from './ProductList.style.ts';

export const ProductList = () => {

  const { productsData } = useContext(ProductsContext);

  return (

    <div style={productListBox}>
      {productsData.map((product) => {
        return (

          <ProductItemContext.Provider value={product} key={product.id} >
            <ProductItem />
          </ProductItemContext.Provider>
        )
      })}
    </div>

  )
}
