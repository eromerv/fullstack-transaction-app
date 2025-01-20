import axios from 'axios';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import {
  fetchTransactions,
  deleteTransaction,
  updateTransaction,
  createTransaction,
} from '../api/transactionApi';
import { Transaction } from '../model/transaction';
import { useState } from 'react';

export const useTransaction = () => {
  const queryClient = useQueryClient();
  const [transactionError, setTransactionError] = useState<string | null>(null);

  const handleError = (error: unknown) => {
    if (axios.isAxiosError(error)) {
      const statusCode = error.response?.status;
      const errorMessage = error.response?.data?.message || 'An error occurred';

      console.error(`Error (${statusCode}): ${errorMessage}`);

      // Custom handling for specific HTTP status codes
      if (statusCode === 429) {
        setTransactionError(
          'Too Many Requests (429): Please slow down and try again later.'
        );
      } else if (statusCode === 404) {
        setTransactionError('Resource not found (404).');
      } else if (statusCode === 500) {
        setTransactionError(
          'Internal server error (500). Please try again later.'
        );
      } else {
        setTransactionError(`Error (${statusCode}): ${errorMessage}`);
      }
    } else {
      setTransactionError('An unexpected error occurred');
    }
  };

  const transactionsQuery = useQuery<Transaction[], Error>({
    queryKey: ['transactions'],
    queryFn: fetchTransactions,
  });

  const createTransactionMutation = useMutation({
    mutationFn: (data: Omit<Transaction, 'id'>) => createTransaction(data),
    onError: handleError,
    onSuccess: () => {
      queryClient.invalidateQueries({
        queryKey: ['transactions'],
      });
    },
  });

  const updateTransactionMutation = useMutation({
    mutationFn: ({ id, data }: { id: number; data: Partial<Transaction> }) =>
      updateTransaction(id, data),
    onError: handleError,
    onSuccess: () => {
      queryClient.invalidateQueries({
        queryKey: ['transactions'],
      });
    },
  });

  const deleteTransactionMutation = useMutation({
    mutationFn: (id: number) => deleteTransaction(id),
    onError: handleError,
    onSuccess: () => {
      queryClient.invalidateQueries({
        queryKey: ['transactions'],
      });
    },
  });

  return {
    transactionsQuery,
    transactionError,
    createTransactionMutation,
    updateTransactionMutation,
    deleteTransactionMutation,
    clearError: () => setTransactionError(null),
  };
};
