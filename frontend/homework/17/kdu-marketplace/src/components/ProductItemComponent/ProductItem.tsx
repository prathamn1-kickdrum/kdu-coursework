import { IProduct } from '../../types/KduMarketPlace.tsx';
import { pricePara, imageBox, img, linkStyle, productCard, titleBox } from './ProductItem.style.ts';
import { Link } from 'react-router-dom';

interface Props {
  productItem: IProduct
}


export const ProductItem = ({ productItem }: Props) => {

  const { id, image, price, title } = productItem;


  return (

    productItem &&

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
