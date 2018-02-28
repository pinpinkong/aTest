# TestProblem
Personal expenses management application that allows users to track how much money have they spent.
(Little changed output formating to be more clear and show only entered date expenses on use of add command, 
only list command shows all expenses for all dates.
And currencies exchange rates are requested only at application start, not every time on total command execute)

Application could be run from precompiled jar file TestProblem.jar in jar folder 
or from Main.class as java application. Tests in test folder, could be run all together by suite AllTests.class
To run from command line use command:
java -jar TestProblem.jar
executed from files root directory

# List of commands  
add, list, clear, total, currencies, exchangeRates, help, exit

add 2017-04-25 12 USD Jogurt  
 -- adds expences entry to the list of user expences. Expences for various dates could be added in any order. Command accepts following parameters:
2017-04-25 - is the date when expences occured(choose date between 2000-2050)
12 - is an amount of money spent
USD - the currency in which expense occured
Jogurt - is the name of product purchased (allows products name up to 29 symbols)

list
 -- shows the list of all expenses sorted by date

clear 2017-04-25
 -- removes all expenses for specified date, where 
2017-04-25 - is the date on which expenses should be removed

total PLN
 -- this command calculates the total amount of money spent and present it to use in specified currency, where
PLN - is the currency in which total amoun of expenses shoul be presented

currencies
 -- list of currencies you can use in programm

exchangeRates
 -- exchange rates of available currencies

help
 -- presents list of available commands

exit
 -- stops programm 
