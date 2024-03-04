import styled from "styled-components"
import NavButton from "./NavigationButton"

const DashboardStyled = styled.div`
    padding-inline: 20px;
`

const Dashboard = () => {
    return (
        <DashboardStyled>
            <NavButton />
        </DashboardStyled>
    )
}

export default Dashboard;