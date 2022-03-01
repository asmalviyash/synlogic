# Getting Started
Fetch the project using command `git clone https://github.com/asmalviyash/synlogic.git` on command line terminal. 
 Import the project as maven project and build it.
 If using command line terminal run `mvn clean install` for building.


## Running the project 
### Using IDE 
Run application as java application, if asked please select `SynalogikrestApplication` as main class

Application will be available at http://localhost:8080

## Testing the endpoint
Using postman 
* Select POST as action 
* Select Body -> form-data -> file as KEY
* Select required text file as VALUE
* Send post request to http://localhost:8080/upload-file

For better format of response please check body as JSON

JSON should look like below

```
[
    "Word count 5",
    "Average word length 3.000",
    "Number of words of length 1 is 2",
    "Number of words of length 2 is 1",
    "Number of words of length 4 is 1",
    "Number of words of length 7 is 1",
    "The most frequently occuring word length is 2 for word lengths of 1"
]
```