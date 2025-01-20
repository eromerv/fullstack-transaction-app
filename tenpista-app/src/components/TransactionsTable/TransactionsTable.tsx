import React from 'react';
import { DataGrid, GridColDef } from '@mui/x-data-grid';
import { Paper, Typography, IconButton, Box } from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import { Transaction } from '../../model/transaction';

interface Props {
  transactions: Transaction[] | undefined;
  onEdit: (id: number) => void;
  onDelete: (id: number) => void;
}

const TransactionsTable: React.FC<Props> = ({ transactions, onEdit, onDelete }) => {

  if (!transactions || transactions.length === 0) {
    return (
      <Typography variant="h6" align="center" style={{ marginTop: '20px' }}>
        No transactions available.
      </Typography>
    );
  }

  const columns: GridColDef[] = [
    { field: 'id', headerName: 'ID', width: 150 },
    { field: 'amount', headerName: 'Amount', width: 150, },
    { field: 'merchant', headerName: 'Merchant', width: 200 },
    { field: 'customer', headerName: 'Customer', width: 200 },
    { field: 'transactionDate', headerName: 'Transaction Date', width: 250 },
    {
      field: 'actions',
      headerName: '',
      disableColumnMenu: true,
      sortable: false,
      renderCell: (params) => (
        <Box display="flex" justifyContent="center" alignItems="center" width="100%">
          <IconButton 
            size="small" 
            onClick={() => onEdit(params.row.id)}
            style={{ marginRight: '8px' }}
          >
            <EditIcon />
          </IconButton>
          <IconButton 
            size="small" 
            onClick={() => onDelete(params.row.id)}
          >
            <DeleteIcon />
          </IconButton>
        </Box>
      ),
    },
  ];

  return (
    <Paper sx={{ height: 400, width: '100%' }}>
      <DataGrid
        rows={transactions}
        columns={columns}
        initialState={{ pagination: { paginationModel: { pageSize: 5 } } }}
        pageSizeOptions={[5, 10]}
        disableRowSelectionOnClick
        sx={{ border: 0 }}
      />
    </Paper>
  );
};

export default TransactionsTable;
