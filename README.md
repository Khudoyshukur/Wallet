# Wallet
#### Video Demo:  https://youtu.be/krXpvpIDqPs
#### Description:
Wallet control

Wallet control is an android application, in which users can manage their
incomes and expenses. It uses Room database for tracking them.

# OnBoarding
The app starts with on boarding screen, which is shown once only.

# Main Screen
In the main screen on the top there is a welcome text and history button
that navigates to transaction history page. Income button is used to insert income
to the database. Income is consists of income amount, income category and income details.
There is also an expense button to insert your expense. Expense also has the same parts as income.

# History screen
This screen shows all transactions including income records, expense records and debt records.

# Debts
In the app we can built-in categories for income and expenses. If user selects debt category for
an expense, this transaction is saved as debt and shown debts page. On the debts page you can revert
that debt by clicking on top of them and money automatically added to your total balance.

# Settings
There is also a settings page where you can insert new categories for income and expenses. Also you can change username on settings page.

# Design
Used comunity figma design https://www.figma.com/file/c2MLLZ51p2YsPP6urlOt9w/Manage-Money-Mobile-App-Community
with some own creative screens :)

# Codebase
I used Android Studio for creating this app.
Navigation Component Jetpack Library has been used for app navigation.
Ui layer is built using MVVM Architecture. App is SingleActivity app.
For dependency injection I used Hilt by Google.

# Architecture
The architecture is Clean Architecture recommended by Google. App consists of two
modules:
    # App Module
    # Shared Module
Ui layer is inside app module.
Data and Domain layers are inside shared module.

# Problem encountered.
I was using Room database for the project. I was
observing the database changes using flows. But the problem was that, when I insert
new income or expense data into database, main screen was not automatically updated.
Then I realized that I didn't make Database object Singleton, that is why two screens are using
different objects of the Database. It was the couse for that main screen not to be
automatically updated. I spent hours on that problem. But the solution was so simple.
