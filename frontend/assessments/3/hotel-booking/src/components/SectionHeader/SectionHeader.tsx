import React from 'react'
import './SectionHeader.scss'

interface IHeadingProp {
    heading: string;
}

const SectionHeader:React.FC<IHeadingProp> = (props:IHeadingProp) => {
  return (
    <div className='section-header-container'>
        <h1>{props.heading}</h1>
    </div>
  )
}

export default SectionHeader