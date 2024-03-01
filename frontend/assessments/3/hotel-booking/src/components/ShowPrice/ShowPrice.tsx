import { useUserRoomSelector } from "../../redux/Store";
import "./ShowPrice.scss";

const ShowPrice: React.FC = () => {
    const price = useUserRoomSelector((state) => state.userRoom.cost);
    const days = useUserRoomSelector((state) => state.userRoom.bookingDays);

    return (
        <div className="price-container">
            {days !== undefined && days > 0 ? (
                <h2>
                    Cost + 18% GST = <span>{price}</span>
                </h2>
            ) : (
                <p>Please select booking dates above.</p>
            )}
        </div>
    );
};

export default ShowPrice;
