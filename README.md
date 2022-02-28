# Getting Started
Fetch the project using command `git clone https://github.com/asmalviyash/synlogic.git` on command line terminal. 
 Import the project as maven project and build it.
 If using command line terminal run `mvn clean install` for building.


## Running the project 
### Using IDE 
Run applicaiton as java application, if asked please select `SynalogikrestApplication` as main class
### Using docker
Below command will be run where dockerfile is present on command prompt
* `mvn clean install`
* `docker build -t dcc/synlogicrest:latest .`
* `docker run  -it  --rm -d -p 8080:8080 --name synlogicrest dcc/synlogicrest:latest`

For stopping
* `docker stop synlogicrest`

Application will be available at http://localhost:8080

## Testing the endpoint
Using postman 
* Select POST as action 
* Select Body -> form-data -> file as KEY
* Select required text file as VALUE
* Send post request to http://localhost:8080/upload-file

For better foramt of response please check body as JSON

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