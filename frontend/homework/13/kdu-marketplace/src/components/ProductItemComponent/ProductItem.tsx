import { useContext } from 'react'
import { ProductItemContext } from '../../context/ProductItemContext/ProductItemContext.tsx'
import { pricePara, imageBox, img, linkStyle, productCard, titleBox } from './ProductItem.style.ts';
import { Link } from 'react-router-dom';

export const ProductItem = () => {

  const { id, image, price, title } = useContext(ProductItemContext);

  return (
    <div style={productCard} id={`${id}`}>

      <div className="imageBox" style={imageBox}>
        <img src={image} alt="" style={img} />
      </div>

      <div className="titleBox" style={titleBox}>
        <p>{title.length < 20 ? title : `${title.substring(0, 20)}...`}</p>
        <p style={pricePara}>$ {price}</p>
      </div>

      <Link style={linkStyle} to={"product/" + id}></Link>

    </div>
  )
}
