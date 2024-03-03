import React from 'react'
import { footerBox } from './Footer.style'

const Footer:React.FC = () => {
  return (
    <div className='footerBox' style={footerBox}>
        <p>&#169; Pratham Nagaria, KDU 2024</p>
    </div>
  )
}

export default Footer