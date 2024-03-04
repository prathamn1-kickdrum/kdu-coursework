import React, { useState } from 'react';
import { DateRangePicker } from "@mui/x-date-pickers-pro/DateRangePicker";
import SectionHeader from "../SectionHeader/SectionHeader";
import { LocalizationProvider } from '@mui/x-date-pickers';
import { AdapterMoment } from '@mui/x-date-pickers/AdapterMoment'
import moment from 'moment';
import { useUserRoomDispatch } from '../../redux/Store';
import { setBookingDays, updatePrice } from '../../redux/slice/UserSlice';
import './DateSelect.scss'

export const DateSelect: React.FC = () => {
    const dispatch = useUserRoomDispatch();
    const [selectedDateRange, setSelectedDateRange] = useState<[Date | null, Date | null]>([null, null]);

    const handleDateChange = (dateRange: [Date | null, Date | null]) => {
        setSelectedDateRange(dateRange);

        const checkInDate = dateRange[0];
        const checkOutDate = dateRange[1];
        const differenceInDays = checkInDate && checkOutDate ? moment(checkOutDate).diff(moment(checkInDate), 'days') : null;
        console.log('Difference in days:', differenceInDays);

        const isValid = checkInDate && checkOutDate && moment(checkInDate).isBefore(checkOutDate);
        
        if(isValid && differenceInDays!==null) {
            dispatch(setBookingDays(differenceInDays));
            dispatch(updatePrice());
        }
    };

    return (
        <div>
            <SectionHeader heading={"Select Date"} />
            <div className="date-container">
                <LocalizationProvider dateAdapter={AdapterMoment}>
                    <DateRangePicker
                        value={selectedDateRange}
                        onChange={handleDateChange}
                        localeText={{ start: "Check-in", end: "Check-out" }}
                    />
                </LocalizationProvider>
            </div>
        </div>
    );
};
