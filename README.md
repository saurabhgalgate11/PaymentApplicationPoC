# Code Test

# About

    This application TEST is to perform general logic of making payment and initialize refund 
    translations. Here we have handled transaction to process payment and making refund towards the 
    limit of transaction amount we have added to the account.

# Steps towards code improvement

    1. Adding validations to the code to avoid app crash on empty input.
    2. Added validations to Edit text to inser only number as input and avoid string values.

# Code Structure formatting

    Following MVVM design pattern to make app flexible and seperate code to handle differen things
    understandable and easy to locate.

# Approach to check refund eligibility

    Calculating previous transaction amount total and calculating total refund transaction 
    need to verify if user is eligible to process payment. Else User will able to see error message
    as "Insufficient Funds" to process refund.
