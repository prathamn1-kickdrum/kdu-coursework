import React, { useState } from 'react';
import styled from 'styled-components';
import { Stock } from '../app';
import { useNavigate } from 'react-router-dom';
import { store } from '../redux/store/store';
import { resetGraph } from '../redux/slice/StockGraphSlice';
import {v4 as uuidv4} from 'uuid';

const CompanyListStyled = styled.div`
  position: relative;
`;

const MoreButton = styled.div`
  cursor: pointer;
  flex-grow: 1;
  height: 100%;
`;

const StockList = styled.ul<{ open: boolean }>`
  position: absolute;
  top: 100%;
  left: 0;
  width: 100%;
  max-height: 200px;
  z-index: 2;
  overflow-y: auto;
  border: 1px solid #ccc;
  border-top: none;
  padding: 0;
  margin-top: 5px;
  list-style-type: none;
  background-color: #fff;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
  border: 1px solid black;
  display: ${(props) => (props.open ? 'block' : 'none')};

  &::-webkit-scrollbar {
  display: none;
}
`;

const StockItem = styled.li`
  padding: 10px;
  cursor: pointer;
  border: 1px solid black;

  &:hover {
    background-color: #f5f5f5;
  }
`;

const StockIcon = styled.div`
    border: 1px solid ${props => props.theme.colors.yellowPrimary};
    text-align: center;
    color: ${props => props.theme.colors.yellowPrimary};
    background-color: ${props => props.theme.colors.yellowSecondary};
    padding: 5px;
`

const StockName = styled.div`
    display: flex;
    gap: 30px;
    align-items: center;
    justify-content: space-between;
    font-size: 1.2rem;
    font-weight: normal;
    height: 100%;
    padding-inline: 10px;
`

interface ScrollableDropdownProps {
    children: React.ReactNode;
    stocks: Stock[];
}

const CompanyList = ({ children, stocks }: Readonly<ScrollableDropdownProps>) => {
    const [isOpen, setIsOpen] = useState<boolean>(false);
    const navigate = useNavigate();

    const toggleDropdown = () => {
        setIsOpen(!isOpen);
    };

    const handleSelectOption = (option: string) => {
        // setSelectedOption(option);
        navigate(`/details/${option}`)
        setIsOpen(false);
        store.dispatch(resetGraph())
    };

    return (
        <CompanyListStyled>
            <MoreButton onClick={toggleDropdown}>
                {children}
            </MoreButton>
            <StockList open={isOpen}>
                {stocks.map((option) => (
                    <StockItem key={uuidv4()} onClick={() => handleSelectOption(option.stock_symbol)}>
                        <StockName>
                            <StockIcon>
                                {option.stock_symbol.substring(0, 3).toUpperCase()}
                            </StockIcon>

                            {option.stock_name}
                        </StockName>
                    </StockItem>
                ))}
            </StockList>
        </CompanyListStyled>
    );
};

export default CompanyList;