export interface Transaction {
  id: number;
  amount: number;
  merchant: string;
  customer: string;
  transactionDate: string; // ISO string
}
