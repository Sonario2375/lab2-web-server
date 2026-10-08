# Lab 2 Web Server -- Project Report

## What I specified

I specified I would: create a new error page for requests with an unknown path that shows you the request path and status and a test to see if it worked correctly, a new feature that when the page recieves "/time" it returns the current server time as a JSON and a test that inyects a specific timestamp to test the feature, and finally to enable HTTP/2 with a self signed certificate over TLS, linking localghost to 127.0.0.1 in the certificate.

## What I changed

error.html => the html for the new error page
ErrorPageTest.kt => the test for the error page
TimeComponent.kt => the new /time feature
TimeControllerTest.kt => the tests for the /time feature
src/test/resources/application.ym => configuration for the tests
openssl-localhost.cnf => openssl configuration file with the parameters for the certificate 
src/main/resources/application.yml => configuration file that allows HTTP/2 and TLS

## Technical decisions

I chose to make the html error page more simple so that the status and the path were completely visible. I also chose in the tests for '/time' to do the timestamp inyection test because i wanted to make sure that the feature worked correctly.

## How I verified

I used the ./gradlew check to see if there were any compilation errors, most of my mistakes were from incorrect format and missing imports and I fixed them by adding the imports and running a './gradlew klintFormat'. I also used the curl checks in the guide. I also tried the features with './gradlew bootRun.

## AI disclosure

- **Tools / skills:** basic Gemini
- **Purpose:** explaining the code and commands that the teacher gave us and also writing the basic structure and style of `error.html` (I later added the "a step further" part)
- **Representative prompts:** generate a simple error.html
- **Affected files/sections:** error.html
- **Validation steps:** checking the html
- **Citations:** 
- **Human-reviewed:** I had to change the messages of the title and subtitle in the error.html as they were in english and a little too bland.
