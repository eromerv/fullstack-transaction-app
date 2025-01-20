import axios from 'axios';
import config from '../config';
import { Transaction } from '../model/transaction';

const axiosInstance = axios.create({
  baseURL: config.api.baseURL,
  timeout: 15000,
  headers: {
    'Content-Type': 'application/json',
  },
});

export const fetchTransactions = async (): Promise<Transaction[]> => {
  const response = await axiosInstance.get<Transaction[]>('/transaction');
  return response.data;
};

export const createTransaction = async (
  data: Omit<Transaction, 'id'>
): Promise<Transaction> => {
  const response = await axiosInstance.post<Transaction>('/transaction', data);
  return response.data;
};

export const updateTransaction = async (
  id: number,
  data: Partial<Transaction>
): Promise<Transaction> => {
  const response = await axiosInstance.put<Transaction>(
    `/transaction/${id}`,
    data
  );
  return response.data;
};

export const deleteTransaction = async (id: number): Promise<void> => {
  await axiosInstance.delete(`/transaction/${id}`);
};
