-- public.transactions definition

-- Drop table

-- DROP TABLE public.transactions;

CREATE TABLE public.transactions (
	id serial4 NOT NULL,
	amount int4 NOT NULL,
	merchant varchar(255) NOT NULL,
	customer varchar(255) NOT NULL,
	transaction_date timestamp NOT NULL,
	updated_at timestamp NOT NULL,
	CONSTRAINT transactions_pkey PRIMARY KEY (id)
);

-- Initial data for transactions table

INSERT INTO public.transactions (id, amount, merchant, customer, transaction_date, updated_at) VALUES
(1, 1200, 'Amazon', 'John Doe', '2025-01-10 14:23:45', '2025-01-10 14:23:45'),
(2, 450, 'Ebay', 'Jane Smith', '2025-01-08 09:10:32', '2025-01-08 09:10:32'),
(3, 7800, 'Walmart', 'Alice Johnson', '2025-01-05 18:47:21', '2025-01-05 18:47:21'),
(4, 2500, 'Target', 'Bob Brown', '2025-01-04 12:11:09', '2025-01-04 12:11:09'),
(5, 320, 'Netflix', 'Carol White', '2025-01-03 20:45:00', '2025-01-03 20:45:00'),
(6, 1500, 'Spotify', 'Dan Black', '2025-01-02 10:15:30', '2025-01-02 10:15:30'),
(7, 500, 'Apple Store', 'Emily Green', '2025-01-01 16:30:15', '2025-01-01 16:30:15'),
(8, 2000, 'Best Buy', 'Frank Harris', '2025-01-01 08:05:55', '2025-01-01 08:05:55'),
(9, 3000, 'Costco', 'Grace Lee', '2024-12-31 14:40:25', '2024-12-31 14:40:25'),
(10, 700, 'Uber Eats', 'Henry Adams', '2024-12-30 19:50:10', '2024-12-30 19:50:10');

SELECT setval('transactions_id_seq', (SELECT MAX(id) FROM transactions) + 1);

