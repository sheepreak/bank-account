# Bank account application

## Steps :

- Define the needs (see Endpoints)
- BDD without cucumber (use mockmvc on integration tests and nested tests)

## Endpoints 

- POST /accounts/{account-id}/deposit : body : {value: double} -> double : expects a value, adds the value to your bank account if it exists, and returns the current balance
- POST /accounts/{account-id}/withdrawal : body : {value: double} -> double : expects a value, substracts the value from your bank account, and returns the current balance
  - I chose not to permit an account to be negative to show how I'd handle an error on the domain
- GET /accounts/{account-id}/history : -> returns an object containing a list of operations with the following fields : 
  - Operation type (currently WITHDRAWAL and DEPOSIT, but could have added an ERROR type to show the failed operations)
  - Date time
  - Previous balance (before the operation)
  - Current balance (after the operation)
  - Amount of the operation