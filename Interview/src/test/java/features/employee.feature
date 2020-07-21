Feature: empolyee details

@Smoke
Scenario Outline: Get Employee details
     Given Header of get product
     When User call "GetEmployeeApi" with "GET" https request
     Then The Api call got success with Status code "200"
     And  User Will get the response as JSON
     And  user will get "<Status>","<Age>","<Role>","<Dob>","<Message>"
     And  user will get Company name as "<company>"

     Examples:
              |Status|Age|Role                   |Dob       |Message                  |comapany    |
              |200   |25 |QA Automation Developer|25-02-1994|data retrieved successful|ABC Infotech|
