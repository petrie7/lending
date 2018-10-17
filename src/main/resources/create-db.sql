CREATE TABLE loan (
  id       INTEGER PRIMARY KEY,
  borrower VARCHAR(30),
  amount  INTEGER
);

CREATE TABLE investment (
  id       INTEGER PRIMARY KEY,
  loan_id,
  lender VARCHAR(30),
  amount  INTEGER
  FOREIGN KEY (loan_id) REFERENCES loan(id)
)