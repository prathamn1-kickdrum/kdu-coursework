import { BrowserRouter, Route, Routes } from 'react-router-dom'
import { HomePage } from '../HomePage/HomePage'
import { ProductPage } from '../ProductPage/ProductPage'
import { PageNotFound } from '../PageNotFound/PageNotFound'
import { Navbar } from '../../components/NavbarComponent/Navbar'
import { useEffect, useState } from 'react'
import { useProductDispatch, useProductSelector } from '../../redux/store'
import { fetchApi, setError, setProductsData, setSuccess } from '../../redux/ProductSlice'
import { IProduct } from '../../types/KduMarketPlace'
import Snackbar from '@mui/material/Snackbar';
import IconButton from '@mui/material/IconButton';
import CloseIcon from '@mui/icons-material/Close';



export const KduMarketPlace = () => {

    const apiData = useProductSelector((state) => state.product.apiData);
    const sortValue = useProductSelector((state) => state.product.sortValue);
    const categoryValue = useProductSelector((state) => state.product.categoryValue);
    const searchValue = useProductSelector((state) => state.product.success);
    const dispatch = useProductDispatch();
    const error = useProductSelector((state) => state.product.error);
    const success = useProductSelector((state) => state.product.success);
    const [openSuccessBar, setOpenSuccessBar] = useState<boolean>(false);
    const [openErrorBar, setOpenErrorBar] = useState<boolean>(false);
    const vertical = "bottom";
    const horizontal = "center";


    useEffect(() => {


        let newData = [...apiData];

        console.log("use-effect", sortValue, categoryValue, searchValue);

        if (categoryValue === "all" || categoryValue == "") {

            console.log(1);

            if (searchValue !== "") {
                newData = newData.filter((curr) => {
                    return curr.title.includes(searchValue!);
                })
            }

            dispatch(setProductsData([...newData]));
        }
        else {

            console.log(2, "apidata: ", apiData.length);

            newData = apiData.filter((curr) => {
                return curr.category === categoryValue
            });

            console.log(newData.length);


            if (searchValue !== "") {
                newData = newData.filter((curr) => {
                    return curr.title.includes(searchValue!);
                })
            }

            dispatch(setProductsData([...newData]));
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
            dispatch(setProductsData([...newData]));
        }
        else if (sortValue === "descending") {
            console.log(4);
            newData = newData.sort(descSort);
            dispatch(setProductsData([...newData]));
        }

        console.log(5);

    }, [sortValue, categoryValue, searchValue]);


    useEffect(() => {
        if (error && error.length > 0) {
            setOpenErrorBar(true);
        }
    }, [error])


    useEffect(() => {
        if (success && success.length > 0) {
            setOpenSuccessBar(true);
        }
    }, [success])



    useEffect(() => {
        try {
            dispatch(fetchApi())
            dispatch(setSuccess("Data fetched successfully"));
        }
        catch {
            dispatch(setError("Failed to fetch!!"));
        }
    }, [dispatch])

    const handleCloseError = (event: React.SyntheticEvent | Event, reason?: string) => {
        if (reason === 'clickaway') {
            return;
        }

        setOpenErrorBar(false);
    };

    const handleCloseSuccess = (event: React.SyntheticEvent | Event, reason?: string) => {
        if (reason === 'clickaway') {
            return;
        }

        setOpenSuccessBar(false);
    };

    const actionError = (
        <IconButton
            size="small"
            aria-label="close"
            color="inherit"
            onClick={handleCloseError}
        >
            <CloseIcon fontSize="small" />
        </IconButton>
    );

    const actionSuccess = (
        <IconButton
            size="small"
            aria-label="close"
            color="inherit"
            onClick={handleCloseSuccess}
        >
            <CloseIcon fontSize="small" />
        </IconButton>
    );



    return (
        <>
            <BrowserRouter>
                <Navbar />

                <Routes>
                    <Route path="/" element={<HomePage />} />
                    <Route path="/product/:uniqueId" element={<ProductPage />} />
                    <Route path="*" element={<PageNotFound />} />
                </Routes>
            </BrowserRouter>

            <Snackbar
                anchorOrigin={{ vertical, horizontal }}
                open={openSuccessBar}
                onClose={handleCloseSuccess}
                message={success}
                action={actionSuccess}
                autoHideDuration={3000}
                ContentProps={{
                    sx: {
                        background: "green",
                        color: 'white'
                    }
                }}
            />

            <Snackbar
                anchorOrigin={{ vertical, horizontal }}
                open={openErrorBar}
                onClose={handleCloseError}
                message={error}
                action={actionError}
                autoHideDuration={3000}
                ContentProps={{
                    sx: {
                        background: "red",
                        color: 'white'
                    }
                }}
            />
        </>
    )
}
