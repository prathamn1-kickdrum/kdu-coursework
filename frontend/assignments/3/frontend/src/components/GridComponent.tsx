import styled from 'styled-components';

const GridContainer = styled.div`
  display: grid;
  grid-template-columns: repeat(8, 170px);
  grid-template-rows: repeat(8, 175 px);
  margin-bottom: 30px;
  border: 2px solid black;
  height: 700px;
`;

const GridCell = styled.div`
  border: 1px dashed #ccc;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const GridComponent = () => {
  return (
    <GridContainer>
      {Array.from({ length: 24 }, (_, index) => (
        <GridCell key={index}>
        </GridCell>
      ))}
    </GridContainer>
  );
};

export default GridComponent;
