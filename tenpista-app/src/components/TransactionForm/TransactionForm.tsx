import React, { useEffect, useState } from 'react';
import { format } from 'date-fns';
import {
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Button,
  TextField,
} from '@mui/material';
import { Transaction } from '../../model/transaction';
import { getLocalDateTime } from '../../util/getLocalDate';

interface Props {
  open: boolean;
  transaction: Transaction | null;
  onClose: () => void;
  onSave: (updatedTransaction: Transaction) => void;
}

const initialFormData = {
  amount: '',
  merchant: '',
  customer: '',
  transactionDate: '',
};

const TransactionForm: React.FC<Props> = ({
  open,
  transaction,
  onClose,
  onSave,
}) => {
  const [formData, setFormData] = useState(initialFormData);
  const [errors, setErrors] = useState(initialFormData);

  useEffect(() => {
    if (transaction) {
      setFormData({
        amount: transaction.amount?.toString() || '',
        merchant: transaction.merchant,
        customer: transaction.customer,
        transactionDate: transaction.transactionDate || getLocalDateTime(),
      });
    } else {
      setFormData({ ...initialFormData, transactionDate: getLocalDateTime() });
    }
  }, [transaction, open]);

  const validate = () => {
    const newErrors = {
      amount: '',
      merchant: '',
      customer: '',
      transactionDate: '',
    };

    if (!formData.amount.trim()) {
      newErrors.amount = 'The amount is required.';
    } else if (isNaN(Number(formData.amount)) || Number(formData.amount) <= 0) {
      newErrors.amount = 'The amount must be a positive number.';
    }

    if (!formData.merchant.trim()) {
      newErrors.merchant = 'The merchant (comercio) name is required.';
    }

    if (!formData.customer.trim()) {
      newErrors.customer = 'The customer name (tenpista) is required.';
    }

    if (!formData.transactionDate) {
      newErrors.transactionDate = 'The transaction date is required.';
    } else if (new Date(formData.transactionDate) > new Date()) {
      newErrors.transactionDate =
        'The transaction date must not be set to a future date and time.';
    }

    setErrors(newErrors);
    return Object.values(newErrors).every((error) => !error);
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSave = () => {
    if (validate()) {
      if (transaction) {
        onSave({
          ...transaction,
          ...formData,
          amount: Number(formData.amount),
          transactionDate: format(
            new Date(formData.transactionDate),
            'yyyy-MM-dd HH:mm:ss'
          ),
        });
      } else {
        onSave({
          id: 0,
          amount: Number(formData.amount),
          merchant: formData.merchant,
          customer: formData.customer,
          transactionDate: format(
            new Date(formData.transactionDate),
            'yyyy-MM-dd HH:mm:ss'
          ),
        });
      }
      setFormData(initialFormData);
    }
  };

  return (
    <Dialog open={open} onClose={onClose} fullWidth maxWidth="sm">
      <DialogTitle>
        {transaction ? 'Edit Transaction' : 'Create Transaction'}
      </DialogTitle>

      <DialogContent>
        <TextField
          fullWidth
          name="amount"
          margin="normal"
          label="Amount"
          value={formData.amount}
          onChange={handleChange}
          error={!!errors.amount}
          helperText={errors.amount}
        />
        <TextField
          fullWidth
          margin="normal"
          label="Merchant"
          name="merchant"
          value={formData.merchant}
          onChange={handleChange}
          error={!!errors.merchant}
          helperText={errors.merchant}
        />
        <TextField
          fullWidth
          margin="normal"
          label="Customer"
          name="customer"
          value={formData.customer}
          onChange={handleChange}
          error={!!errors.customer}
          helperText={errors.customer}
        />
        <TextField
          fullWidth
          margin="normal"
          label="Transaction Date"
          name="transactionDate"
          type="datetime-local"
          value={formData.transactionDate}
          onChange={handleChange}
          error={!!errors.transactionDate}
          helperText={errors.transactionDate}
          slotProps={{
            input: {
              inputProps: { max: new Date().toISOString().slice(0, 16) },
            },
          }}
        />
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose}>Cancel</Button>
        <Button onClick={handleSave} variant="contained" color="success">
          Save
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default TransactionForm;
