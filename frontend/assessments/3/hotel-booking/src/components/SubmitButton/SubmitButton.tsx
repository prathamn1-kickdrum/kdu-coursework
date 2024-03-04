import { useEffect, useState } from 'react';
import { useUserRoomSelector } from '../../redux/Store'
import './SubmitButton.scss'

const SubmitButton = () => {

    const [isValidated,setIsValidated] = useState(false);

    const userBookingDetails = useUserRoomSelector(state => state.userRoom);

    

    useEffect(()=> {
      const validateDetails = () => {
        return userBookingDetails.bookingDays!==undefined && userBookingDetails.bookingDays>0 && userBookingDetails.userRoom!==undefined;
      }
      setIsValidated(validateDetails())
    },[userBookingDetails])

    const handleSubmitClick = () => {
        console.log(userBookingDetails);
    }

  return (
    <button type="button" onClick={handleSubmitClick} disabled={!isValidated}>
        <p>Submit</p>
    </button>
  )
}

export default SubmitButton