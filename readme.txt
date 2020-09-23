## You can start application in
	 eclipse/STS/IntelliJ IDE 
or Standalone service using 
	 	java -jar trade-store-service.jar
	 	
## Application is available at : 
	http://localhost:8080/

## Swagger UI used for testing available at: 
	http://localhost:8080/swagger-ui.html#
	
## You can use below json objects to make tests: 

## maturityDate validation failed, so trade not inserted into store
{
    "tradeId": "T1",
    "version": "1",
    "counterPartyId": "CP-1",
    "bookId": "B1",
    "maturityDate": "20/05/2020",
    "createdDate": "23/09/2020",
    "expired": "N"
}

## Valid trade, so trade inserted
{
    "tradeId": "T2",
    "version": "2",
    "counterPartyId": "CP-2",
    "bookId": "B1",
    "maturityDate": "20/05/2021",
    "createdDate": "23/09/2020",
    "expired": "N"
}

## Trade is already exist, but current version is lower, so trade failed with exception
{
    "tradeId": "T2",
    "version": "1",
    "counterPartyId": "CP-1",
    "bookId": "B1",
    "maturityDate": "20/05/2021",
    "createdDate": "14/03/2015",
    "expired": "N"
}

## maturityDate validation failed, so trade not inserted into store
{
    "tradeId": "T3",
    "version": "3",
    "counterPartyId": "CP-3",
    "bookId": "B2",
    "maturityDate": "20/05/2014",
    "createdDate": "23/09/2020",
    "expired": "N"
}