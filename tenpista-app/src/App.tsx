import './App.css';
import config from './config';
import DashboardIcon from '@mui/icons-material/Dashboard';
import { AppProvider, type Navigation } from '@toolpad/core/AppProvider';
import { DashboardLayout } from '@toolpad/core/DashboardLayout';
import Dashboard from './pages/Dashboard';

const menu: Navigation = [
  {
    segment: '#',
    title: 'Transactions',
    icon: <DashboardIcon />,
  },
];

function App() {
  return (
    <AppProvider
      navigation={menu}
      branding={{
        logo: <img src="https://mui.com/static/logo.png" alt="MUI logo" />,
        title: 'Dashboard',
        homeUrl: config.homeUrl,
      }}
    >
      <DashboardLayout>
        <Dashboard />
      </DashboardLayout>
    </AppProvider>
  );
}

export default App;
