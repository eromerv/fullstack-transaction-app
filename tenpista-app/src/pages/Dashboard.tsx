import { useState } from 'react';
import Grid from '@mui/material/Grid2';
import {
  Alert,
  Box,
  Button,
  Container,
  Skeleton,
  Snackbar,
  Typography,
} from '@mui/material';
import { useTransaction } from '../hooks/useTransaction';
import { Transaction } from '../model/transaction';
import TransactionsTable from '../components/TransactionsTable/TransactionsTable';
import DeleteDialog from '../components/DeleteDialog/DeleteDialog';
import TransactionDialogForm from '../components/TransactionForm/TransactionForm';

const Dashboard = () => {
  const {
    transactionsQuery,
    transactionError,
    createTransactionMutation,
    updateTransactionMutation,
    deleteTransactionMutation,
    clearError,
  } = useTransaction();

  const [transactionId, setTransactionId] = useState<number>(0);
  const [openDeleteDialog, setOpenDeleteDialog] = useState(false);
  const [openEditDialog, setOpenEditDialog] = useState(false);
  const [selectedTransaction, setSelectedTransaction] =
    useState<Transaction | null>(null);

  const { data, isLoading } = transactionsQuery;

  const handleSave = (transaction: Transaction) => {
    if (transaction.id > 0) {
      updateTransactionMutation.mutate({
        id: transaction.id,
        data: {
          amount: transaction.amount,
          merchant: transaction.merchant,
          customer: transaction.customer,
          transactionDate: transaction.transactionDate,
        },
      });
    } else {
      createTransactionMutation.mutate(transaction);
    }
    setOpenEditDialog(false);
  };

  const handleCreateTransaction = () => {
    setSelectedTransaction(null);
    setOpenEditDialog(true);
  };

  const handleEdit = (id: number) => {
    const transaction = data?.find((t) => t.id === id) || null;
    setSelectedTransaction(transaction);
    setOpenEditDialog(true);
  };

  const handleDelete = (id: number) => {
    setTransactionId(id);
    setOpenDeleteDialog(true);
  };

  const handleConfirmDelete = () => {
    if (transactionId > 0) {
      deleteTransactionMutation.mutate(transactionId);
    }
    setOpenDeleteDialog(false);
  };

  console.log(transactionError);

  return (
    <>
      <Snackbar
        open={!!transactionError}
        autoHideDuration={6000}
        onClose={clearError}
        anchorOrigin={{ vertical: 'top', horizontal: 'center' }}
      >
        <Alert onClose={clearError} severity="error">
          {transactionError}
        </Alert>
      </Snackbar>

      <Container>
        <Grid container spacing={2}>
          <Grid size={12}>
            <Box
              display="flex"
              alignItems="center"
              justifyContent="space-between"
            >
              <Typography variant="h4" gutterBottom>
                Transactions
              </Typography>
              <Button
                variant="contained"
                color="primary"
                onClick={handleCreateTransaction}
              >
                Add Transaction
              </Button>
            </Box>
          </Grid>
          <Grid size={12}>
            {isLoading ? (
              <Box>
                <Skeleton animation="wave" height={40} />
              </Box>
            ) : (
              data && (
                <TransactionsTable
                  transactions={data}
                  onEdit={handleEdit}
                  onDelete={handleDelete}
                />
              )
            )}
          </Grid>
          <Grid size={12}>
            <TransactionDialogForm
              open={openEditDialog}
              transaction={selectedTransaction}
              onClose={() => setOpenEditDialog(false)}
              onSave={handleSave}
            />
            <DeleteDialog
              open={openDeleteDialog}
              onClose={() => setOpenDeleteDialog(false)}
              onConfirm={handleConfirmDelete}
            />
          </Grid>
        </Grid>
      </Container>
    </>
  );
};

export default Dashboard;
