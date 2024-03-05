import { FC, useEffect, useState } from "react"
import { ProductsContext } from "./ProductsContext"
import { IProduct, IProductsProvider } from "../../types/KduMarketPlace";

export const ProductsProvider: FC<IProductsProvider> = ({ children }) => {

    const [productsData, setProductsData] = useState<IProduct[]>([]);
    const [apiData, setApiData] = useState<IProduct[]>([]);
    const [categoryValue, setCategoryValue] = useState("");
    const [sortValue, setSortValue] = useState("");
    const [searchValue, setSearchValue] = useState("");
    const apiUrl = "https://fakestoreapi.com/products";

    useEffect(() => {

        fetch(apiUrl)
            .then((res) => {
                return res.json();
            })
            .then((res) => {
                console.log("res: " + res);
                setProductsData(res);
                setApiData(res);
            })

    }, []);


    useEffect(() => {

        console.log("filtering....", categoryValue, sortValue, searchValue);

        let newData = apiData;

        if (categoryValue === "all" || categoryValue == "") {
            console.log(1);

            if(searchValue != ""){
                newData = newData.filter((curr) => {
                    return curr.title.includes(searchValue);
                })
            }

            setProductsData([...newData]);
        }
        else {
            console.log(2);

            newData = apiData.filter((curr) => {
                return curr.category === categoryValue
            });

            if(searchValue != ""){
                newData = newData.filter((curr) => {
                    return curr.title.includes(searchValue);
                })
            }

            console.log(categoryValue);
            console.log(newData);

            setProductsData([...newData]);
        }

        const ascSort = (a: IProduct, b: IProduct) => {
            return a.price - b.price;
        }

        const descSort = (a: IProduct, b: IProduct) => {
            return b.price - a.price;
        }

        if (sortValue === "ascending") {
            console.log(3);
            newData = newData.sort(ascSort);
            console.log(newData);
            setProductsData([...newData]);
        }
        else if (sortValue === "descending") {
            console.log(4);
            newData = newData.sort(descSort);
            console.log(newData);
            setProductsData([...newData]);
        }

        console.log(5);


    }, [sortValue, categoryValue, searchValue]);


    return (
        <ProductsContext.Provider value={{ productsData, sortValue, setSortValue, categoryValue, setCategoryValue, searchValue, setSearchValue }}>
            {children}
        </ProductsContext.Provider>
    )
}