import { useContext } from 'react'
import { ProductsContext } from '../../context/ProductsContext/ProductsContext.tsx';
import { Link, useParams } from 'react-router-dom';
import { button, descBox, detailsBox, heading1, heading2, heading3, imageBox, img, link, productPage, titleBox } from './ProductPage.style.ts';
import { blueSpan } from '../HomePage/HomePage.style.ts';

export const ProductPage = () => {

  const { productsData } = useContext(ProductsContext);

  const { uniqueId } = useParams();

  if (!productsData || productsData.length === 0) {
    return (
      <>
      </>
    )
  }

  const productId = parseInt(uniqueId!);

  const results = productsData.filter((curr) => {
    return curr.id === productId
  });

  const product = results[0];
  const { id, title, image, category, description, price, rating } = product;

  return (

    <div style={productPage}>

      <div style={titleBox}>
        <h1 style={heading1}>{title}</h1>
      </div>

      <div style={detailsBox}>

        <div style={imageBox}>
          <img src={image} alt={`${id}`} style={img} />
        </div>

        <div style={descBox}>
          <h2 style={heading2}>Product: {title}</h2>
          <h2 style={heading2}>Category: {category}</h2>
          <h2 style={{...heading2, ...blueSpan}}>Price: $ {price}</h2>
          <h2 style={heading2}>Rating Count: {rating.count}</h2>
          <h2 style={heading2}>Stars: {rating.rate}</h2>
          <h3 style={heading3}>Description: {description}</h3>
          <button style={button}>
            <Link style={link} to={"/"}>
              Back To Products
            </Link>
          </button>
        </div>
      </div>
    </div>
  )
}
