import { ProductItem } from '../ProductItemComponent/ProductItem.tsx';
import { productListBox } from './ProductList.style.ts';
import { useProductDispatch, useProductSelector } from '../../redux/store.ts';
import { setCategoryValue, setSortValue } from '../../redux/ProductSlice.tsx';
import { Loader } from '../Loader/Loader.tsx';


export const ProductList = () => {

  const params = new URLSearchParams(document.location.search);
  const category = params.get("category");
  const sort = params.get("sort");

  const dispatch = useProductDispatch();

  if (category) {
    dispatch(setCategoryValue(category));
  }

  if (sort) {
    dispatch(setSortValue(sort));
  }

  const productsData = useProductSelector((state) => state.product.productsData);
  const isLoading = useProductSelector((state) => state.product.isLoading);

  console.log("rendering home...", productsData.length);

  return (

    <div style={productListBox}>

      {
        isLoading ?


          productsData.map((product) => {
            return (
              <ProductItem key={product.id} productItem={product} />
            )
          })
          :
          <Loader />
      }
    </div>

  )
}
