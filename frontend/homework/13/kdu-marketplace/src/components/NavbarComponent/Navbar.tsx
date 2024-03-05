import { useContext } from 'react'
import { Search } from '../SearchComponent/Search.tsx'
import { navbarBox, customizationBox, filterBox, filterSpan, filterSelect, filterOptions } from './Navbar.style.ts'
import { ProductsContext } from '../../context/ProductsContext/ProductsContext.tsx'


export const Navbar = () => {

    const { sortValue, setSortValue, categoryValue, setCategoryValue } = useContext(ProductsContext);

    const sortChangeHandler = (event: React.ChangeEvent<HTMLSelectElement>) => {
        setSortValue(event.target.value);
    }

    const categoryChangeHandler = (event: React.ChangeEvent<HTMLSelectElement>) => {
        setCategoryValue(event.target.value);
    }



    return (
        <div className='navbarBox' style={navbarBox}>
            <Search />

            <div className="customizationBox" style={customizationBox}>

                <div className="filterBox" style={filterBox}>
                    <span style={filterSpan}>Category: </span>
                    <select style={filterSelect} name="category" id="category" value={categoryValue} onChange={(e) => { categoryChangeHandler(e) }}>
                        <option style={filterOptions} value="all">All</option>
                        <option style={filterOptions} value="men's clothing">Men's Clothing</option>
                        <option style={filterOptions} value="women's clothing">Women's Clothing</option>
                        <option style={filterOptions} value="electronics">Electronics</option>
                        <option style={filterOptions} value="jewelery">Jewelery</option>
                    </select>
                </div>

                <div className="sortBox" style={filterBox}>
                    <span style={filterSpan}>Sort: </span>
                    <select style={filterSelect} name="sort" id="sort" value={sortValue} onChange={(e) => { sortChangeHandler(e) }}>
                        <option style={filterOptions} value="random"> Random </option>
                        <option style={filterOptions} value="ascending">Ascending</option>
                        <option style={filterOptions} value="descending">Descending</option>
                    </select>
                </div>
            </div>

        </div>
    )
}
