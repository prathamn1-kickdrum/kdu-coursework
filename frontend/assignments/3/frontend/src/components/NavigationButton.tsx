import * as React from 'react';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Box from '@mui/material/Box';
import StocksTable from './StockList';
import WishListTable from './UserWatchlist';

interface TabPanelProps {
    children?: React.ReactNode;
    index: number;
    value: number;
}

function NavButtonContainer(props: Readonly<TabPanelProps>) {
    const { children, value, index, ...other } = props;

    return (
        <div
            role="tabpanel"
            hidden={value !== index}
            id={`simple-tabpanel-${index}`}
            aria-labelledby={`simple-tab-${index}`}
            {...other}
        >
            {value === index && (
                <Box sx={{ p: 3 }}>
                    {children}
                </Box>
            )}
        </div>
    );
}

const NavigationButton = () => {
    const [value, setValue] = React.useState(0);

    const handleChange = (_: React.SyntheticEvent, newValue: number) => {
        setValue(newValue);
    };

    return (
        <Box sx={{ width: '100%' }}>
            <Box>
                <Tabs value={value} onChange={handleChange} aria-label="basic tabs example">
                    <Tab sx={{ fontWeight: 900 }} label="Explore" />
                    <Tab sx={{ fontWeight: 900 }} label="My wishlist" />
                </Tabs>
            </Box>

            <NavButtonContainer value={value} index={0}>
                <StocksTable />
            </NavButtonContainer>
            <NavButtonContainer value={value} index={1}>
                <WishListTable />
            </NavButtonContainer>
        </Box>
    );
}

export default NavigationButton;