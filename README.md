# DockDocker-ServersManagement

What you need to start this API
`1. You need SQL Manager API`
`2  You need SQL server and make sure you are configure the connection data`

call's to this API

Select servers
Example to get all servers
```
localhost:5100/s_select/
```
By select use /SELECT * FROM <table> WHERE <some filter>
-response JSON object

To add server into the database
```
localhost:5100/s_addServer/<username>/<password>/<server name>/<server ip's>/<docker status>
```
if you used Java, convert the link with ASCII characters

Delete server from the databse
```
localhost:5100/s_deleteServer/<id>
```
