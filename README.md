# DockDocker-ServersManagement

What you need to start this API
`1. You need SQL Manager API`
`2  You need SQL server and make sure you are configure the connection data`

call's to this API

Select servers
Example to get all servers
```
localhost:4567/s_select/
```
-response JSON object

To add server into the database
```
localhost:4567/s_addServer/<username>/<password>/<server name>/<server ip's>/<docker status>
```


Delete server from the databse
```
localhost:4567/s_deleteServer/<id>
```
