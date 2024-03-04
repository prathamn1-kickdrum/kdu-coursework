import { useDispatch } from "react-redux";
import styled from "styled-components"
import { addWishList, removeWishList } from "../redux/slice/ApiStocksSlice";
import { useState } from "react";
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';
import ToggleWishlist from '@mui/icons-material/CheckCircle';
import CancelIcon from '@mui/icons-material/Cancel';

const Item = styled.div`
    cursor: pointer;
`

interface ChangingButtonProps {
    index: number,
    wishlist: boolean,
    page: number
}

const AddRemoveWatchButton = ({ index, wishlist, page }: Readonly<ChangingButtonProps>) => {
    const dispatch = useDispatch();
    const [wishlistChild, setWishlistChild] = useState<React.ReactNode>(<ToggleWishlist color='primary' />)


    function removeWishlistState(index: number) {
        if (!page) {
            dispatch(removeWishList({ page, index }));
            return;
        }
        dispatch(removeWishList({ page: page - 1, index }));
    }

    function addWishlistState(index: number) {
        if (!page) {
            dispatch(addWishList({ page, index }));
            return;
        }

        dispatch(addWishList({ page: page - 1, index }));
    }

    function handleMouseEnter() {
        setWishlistChild(<CancelIcon color='error' />)
    }

    function handleMouseLeave() {
        setWishlistChild(<ToggleWishlist color='primary' />)
    }

    return (
        <>
            {
                wishlist ?
                    <Item onClick={() => removeWishlistState(index)} onMouseEnter={handleMouseEnter} onMouseLeave={handleMouseLeave}>
                        {wishlistChild}
                    </Item> :
                    <Item onClick={() => addWishlistState(index)}>
                        <AddCircleOutlineIcon color='primary' />
                    </Item>
            }
        </>
    )
} 

export default AddRemoveWatchButton;